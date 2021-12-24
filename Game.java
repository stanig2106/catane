import globalVariables.GameVariables;
import map.CatanMap;
import player.Player;
import util_my.Promise;
import view.Scene;
import view.View;
import view.scenes.GameScene;
import view.scenes.StartMenuScene;

/**
 * Catan
 * 
 * By Stani Gam, Manon Baha
 * 
 * 01 nov. 2021
 */
public class Game {
   public static void main(String[] args) {
      Promise<Void> mapLoading = new Promise<Void>((resolve, reject) -> {
         System.out.println("mapLoading...");
         GameVariables.map = new CatanMap();
         final Player player = new Player.RealPlayer();
         GameVariables.players = new Player[] { player };
         resolve.accept(null);
      });
      System.out.println("viewLoading...");
      GameVariables.view = new View();
      mapLoading.await();
      GameVariables.scenes.startMenuScene = new StartMenuScene(GameVariables.view);
      GameVariables.scenes.gameScene = new GameScene(GameVariables.view);
      GameVariables.scenes.startMenuScene.enable();
      System.out.println("done");
   }

}
