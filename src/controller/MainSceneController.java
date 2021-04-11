package controller;

import controller.flowcontrol.IChangeScene;

import java.io.IOException;

public class MainSceneController implements IChangeScene {

    public void btnCloseOnAction() throws IOException {
        sceneChanger("welcomescreen");
    }

}
