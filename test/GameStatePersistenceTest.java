import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import play.libs.Json;
import actors.GameActor;
import structures.GameState;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class GameStatePersistenceTest {

    private static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("GameStatePersistenceTestSystem");
    }

    @AfterClass
    public static void teardown() {
        if (system != null) {
            system.terminate();
            system = null;
        }
    }

    @Test
    public void gameState_should_remain_same_instance_across_multiple_events() throws Exception {

        ActorRef out = system.deadLetters();

        // ✅ Akka 正确创建 Actor（不是 new）
        TestActorRef<GameActor> ref =
                TestActorRef.create(system, Props.create(GameActor.class, out));

        // ✅ 拿到真实 actor 实例，才能直接调用方法
        GameActor actor = ref.underlyingActor();

        GameState first = getPrivateGameState(actor);
        assertNotNull("GameState should be created during GameActor construction", first);

        ObjectNode heartbeat = Json.newObject();
        heartbeat.put("messagetype", "heartbeat");
        actor.processMessage("heartbeat", heartbeat);

        ObjectNode other = Json.newObject();
        other.put("messagetype", "otherclicked");
        actor.processMessage("otherclicked", other);

        GameState second = getPrivateGameState(actor);
        assertSame("GameState reference should remain the same across events (SC-501)", first, second);
    }

    private static GameState getPrivateGameState(GameActor actor) throws Exception {
        Field f = GameActor.class.getDeclaredField("gameState");
        f.setAccessible(true);
        return (GameState) f.get(actor);
    }
}