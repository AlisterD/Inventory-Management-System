/**
 * Inventory Management System
 * C482 Software I (Fall 2020)
 * Western Governors University
 *
 * @file Main.java
 * @author Russell Taylor
 * @date 10/14/2020
 *
 * For future updates to this program it would be useful to implement auto-incrementing and decrementing of inventory quantities when parts and products are added and/or removed. This would allow the program to more effectively track inventory on an ongoing basis. In the current version, deleting an item removes it from the program altogether, but in the future version, there would be an additional option to decrement values, including auto-decrementing parts when an associated product is removed. Of course, permanent storage would need to be implemented as well in the future version in order to make this functionality worthwhile.
 */

package main;

import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Begins operation of the program
 */
public class Main extends Application {

    /**
     * Loads and displays the Main screen
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Inventory inv = new Inventory();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        controller.MainController controller = new controller.MainController(inv);
        loader.setController(controller);
        /**
         * @bug fixed: The following line of code caused a few hours of frustration as I was learning how to get the screen to appear. The debugger was giving me a Null Pointer Exception and also objecting to the partial code I had written at the time in MainController.java. First, I learned that MainController needed to be instantiated. I also had to discover that SceneBuilder automatically assigns an incorrect path to a Controller class, while the course webinar videos instructed me to define the Controller class in the code. Finally, I learned that I was continuing to recieve this error message because of minor errors in the FXML document, because I had not included all of the needed instance variables in MainController, and because of errors in the MainController code. Working through this process was valuable because each time I initially launched a new screen, I ran into the same issues.
         */
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Begins operation of the program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
