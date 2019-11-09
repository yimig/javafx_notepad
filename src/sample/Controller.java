package sample;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

public class Controller {
    public CheckMenuItem mchbWrap;
    Robot robot;
    File executeFile;
    public TextArea taResult;
    public Button btnExit;
    public void OpenNewFile(ActionEvent actionEvent)
    {
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setFileFilter(new FileNameExtensionFilter("Text File(*.txt)", "txt"));
        int result=jfc.showDialog(Main.getSwingNode().getContent(),"Open");
        if(result == JFileChooser.APPROVE_OPTION)
        {
            executeFile  = jfc.getSelectedFile();
            taResult.setText("");
            try {
                BufferedReader bufferedReader=new BufferedReader(new FileReader(executeFile));
                String line=bufferedReader.readLine();
                while (line!=null) {
                    taResult.appendText(line);
                    line=bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void SaveFile(ActionEvent actionEvent)
    {
        if(executeFile==null)SaveAs(actionEvent);
        else
        {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(executeFile));
                bufferedWriter.write(taResult.getText());
                bufferedWriter.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void SaveAs(ActionEvent actionEvent)
    {
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setFileFilter(new FileNameExtensionFilter("Text File(*.txt)", "txt"));
        jfc.setSelectedFile(new File("New Document.txt"));
        int result=jfc.showDialog(Main.getSwingNode().getContent(),"Save");
        if(result == JFileChooser.APPROVE_OPTION) {
            executeFile = jfc.getSelectedFile();
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(executeFile));
                bufferedWriter.write(taResult.getText());
                bufferedWriter.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void NewFile(ActionEvent actionEvent)
    {
        taResult.setText("");
    }

    public void ExitApp(ActionEvent actionEvent)
    {
        Main.getStage().close();
    }

    private void SimulateKeyDown(int[] keys)
    {
        if(robot==null) {
            try {
                robot = new Robot();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int key:keys)
        {
            robot.keyPress(key);
        }
        for(int key:keys)
        {
            robot.keyRelease(key);
        }
    }

    public void Undo(ActionEvent actionEvent) {
        int[] keys={KeyEvent.VK_CONTROL,KeyEvent.VK_Z};
        SimulateKeyDown(keys);
    }

    public void Redo(ActionEvent actionEvent) {
        int[] keys={KeyEvent.VK_CONTROL,KeyEvent.VK_Y};
        SimulateKeyDown(keys);
    }

    public void Cut(ActionEvent actionEvent) {
        int[] keys={KeyEvent.VK_CONTROL,KeyEvent.VK_X};
        SimulateKeyDown(keys);
    }

    public void Copy(ActionEvent actionEvent) {
        int[] keys={KeyEvent.VK_CONTROL,KeyEvent.VK_C};
        SimulateKeyDown(keys);
    }

    public void Paste(ActionEvent actionEvent) {
        int[] keys={KeyEvent.VK_CONTROL,KeyEvent.VK_V};
        SimulateKeyDown(keys);
    }

    public void Delete(ActionEvent actionEvent) {
        int[] keys={KeyEvent.VK_CONTROL,KeyEvent.VK_D};
        SimulateKeyDown(keys);
    }

    public void SelectAll(ActionEvent actionEvent) {
        int[] keys={KeyEvent.VK_CONTROL,KeyEvent.VK_A};
        SimulateKeyDown(keys);
    }

    public void WrapChange(ActionEvent actionEvent) {
        taResult.setWrapText(mchbWrap.isSelected());
    }

    public void SelectFont(ActionEvent actionEvent) {
        javafx.scene.text.Font sceneFont=taResult.getFont();
        java.awt.Font awtFont=new java.awt.Font(sceneFont.getName(),0,(int)sceneFont.getSize());
        MQFontChooser fontChooser=new MQFontChooser(awtFont);
        int returnValue = fontChooser.showFontDialog(Main.getSwingNode().getContent());
        if (returnValue == MQFontChooser.APPROVE_OPTION) {
            Font font = fontChooser.getSelectFont();
            taResult.setFont(new javafx.scene.text.Font(font.getName(),font.getSize()));
        }
    }

    public void SelectColor(ActionEvent actionEvent) {
        Color color = JColorChooser.showDialog(Main.getSwingNode().getContent(), "Select Color", null);
        taResult.setStyle("-fx-text-fill:#"+Integer.toHexString(color.getRed())+Integer.toHexString(color.getGreen())+Integer.toHexString(color.getBlue()));
    }

    public void About(ActionEvent actionEvent) {
        try {
            AnchorPane page = FXMLLoader.load(getClass().getResource("aboutForm.fxml"));
            Scene newScene = new Scene(page);
            Stage stage = new Stage();
            stage.setTitle("about");
            stage.setScene(newScene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Close(ActionEvent actionEvent) {
        Stage stage = (Stage)btnExit.getScene().getWindow();
        stage.close();
    }
}
