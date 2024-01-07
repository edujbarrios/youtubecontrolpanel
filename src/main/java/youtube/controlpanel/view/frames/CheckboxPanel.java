package youtube.controlpanel.view.frames;

import youtube.controlpanel.controller.CheckboxFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CheckboxPanel extends JPanel {
    ArrayList<JCheckBox> checkboxes = new ArrayList<>();

    CheckboxPanel() {
        // Crear checkboxes
        JCheckBox viewsCheckbox = CheckboxFactory.createStyledCheckbox("Video views");
        JCheckBox likesCheckbox = CheckboxFactory.createStyledCheckbox("Video likes");
        JCheckBox commentsCheckbox = CheckboxFactory.createStyledCheckbox("Video comments");
        JCheckBox earningCheckbox = CheckboxFactory.createStyledCheckbox("Money earning");

        checkboxes.add(viewsCheckbox);
        checkboxes.add(likesCheckbox);
        checkboxes.add(commentsCheckbox);
        checkboxes.add(earningCheckbox);

        // Añadir checkboxes al panel
        setLayout(new GridLayout(4, 1));
        add(viewsCheckbox);
        add(likesCheckbox);
        add(commentsCheckbox);
        add(earningCheckbox);
    }

    public ArrayList<JCheckBox> getCheckboxes() {
        return checkboxes;
    }
}
