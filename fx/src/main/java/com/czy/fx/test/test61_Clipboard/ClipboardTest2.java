package com.czy.fx.test.test61_Clipboard;

import com.czy.fx.test.FXUtil;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * @author chenzy
 * @since 2020-05-28
 */
public class ClipboardTest2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        var clipboard = Clipboard.getSystemClipboard();

//        clipboard.clear();

        var anchorPane = new AnchorPane();
        var label = new Label("文本额啊");
        var text = new TextField();
        label.setOnDragDetected(event -> {
            var dragboard=label.startDragAndDrop(TransferMode.MOVE);
            var content=new ClipboardContent();
            content.putString(label.getText());
            dragboard.setContent(content);
        });
        text.setOnDragOver(event -> {
//            event.getAcceptedTransferMode()
            event.acceptTransferModes(TransferMode.MOVE);
        });
        text.setOnDragDropped(event -> {
            text.setText(event.getDragboard().getString());
        });

        anchorPane.getChildren().addAll(label,text);

        FXUtil.setDefaultValue(primaryStage, anchorPane);
        AnchorPane.setLeftAnchor(text,label.getWidth()+10);



        var scene = primaryStage.getScene();

    }
}
