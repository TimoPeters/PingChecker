package pingchecker.application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    private boolean keepChecking = false;

    private PingChecker pingChecker = new PingChecker();

    final String NA = "104.160.131.3";
    final String EUW = "104.160.141.3";
    final String EUNE = "104.160.142.3";
    final String OCE = "104.160.156.1";
    final String LAN = "104.160.136.3";


    public Button startButton;
    public Button stopButton;

    public CheckBox euCheckBox;
    public CheckBox euneCheckBox;
    public CheckBox naCheckBox;
    public CheckBox oceCheckBox;
    public CheckBox lanCheckBox;

    public Label pingLabel;


    public void handleStartButtonClick() {
        constantlyUpdateLabel();
        keepChecking = true;
    }

    public void handleStopButtonClick() {
        keepChecking = false;
    }

    public void constantlyUpdateLabel() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (keepChecking && anyBoxChecked()) {
                            pingLabel.setText(pingChecker.getPing(ipToUse()) + " ms");
                        } else {
                            pingLabel.setText("");
                        }
                    }
                });
            }
        }, 0, 500);
    }


    public void checkEuw(ActionEvent actionEvent) {
        uncheckAllExept(euCheckBox);
    }

    public void checkEune(ActionEvent actionEvent) {
        uncheckAllExept(euneCheckBox);
    }

    public void checkNa(ActionEvent actionEvent) {
        uncheckAllExept(naCheckBox);
    }

    public void checkOce(ActionEvent actionEvent) {
        uncheckAllExept(oceCheckBox);
    }

    public void checkLan(ActionEvent actionEvent) {
        uncheckAllExept(lanCheckBox);
    }

    public boolean anyBoxChecked() {
        if (euCheckBox.isSelected() ||
                euneCheckBox.isSelected() ||
                naCheckBox.isSelected() ||
                oceCheckBox.isSelected() ||
                lanCheckBox.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    public String ipToUse() {
        if (euCheckBox.isSelected()) {
            return EUW;
        } else if (euneCheckBox.isSelected()) {
            return EUNE;
        } else if (naCheckBox.isSelected()) {
            return NA;
        } else if (oceCheckBox.isSelected()) {
            return OCE;
        } else if (lanCheckBox.isSelected()) {
            return LAN;
        } else {
            return "";
        }
    }

    private void uncheckAllExept(CheckBox checkBox) {

        if (!checkBox.equals(euCheckBox)) {
            euCheckBox.setSelected(false);
        }

        if (!checkBox.equals(euneCheckBox)) {
            euneCheckBox.setSelected(false);
        }

        if (!checkBox.equals(naCheckBox)) {
            naCheckBox.setSelected(false);
        }

        if (!checkBox.equals(oceCheckBox)) {
            oceCheckBox.setSelected(false);
        }

        if (!checkBox.equals(lanCheckBox)) {
            lanCheckBox.setSelected(false);
        }

    }
}
