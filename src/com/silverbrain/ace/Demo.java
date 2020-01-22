package com.silverbrain.ace;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import com.silverbrain.ace.events.*;

public class Demo extends Application {

	private Scene scene;
	private AceEditorJFX aceEditorJFX;
	
	public void start(Stage primaryStage) {
	
		aceEditorJFX = new AceEditorJFX();
		aceEditorJFX.getEditor().on(AceEvent.READY, (event) -> {
			registerEventHandlers();
			primaryStage.show();
		});
		scene = new Scene(aceEditorJFX, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AceEditorJFX");
		String imageURL = getClass().getClassLoader().getResource("ace.png").toString();
		primaryStage.getIcons().add(new Image(imageURL));
		
		
	}
	
	private void registerEventHandlers() {
		aceEditorJFX.getEditor().on(AceEvent.CHANGE, (event) -> {
			
		});
	}

}