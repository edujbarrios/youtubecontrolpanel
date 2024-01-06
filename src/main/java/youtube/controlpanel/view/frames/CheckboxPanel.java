package youtube.controlpanel.view.frames;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckboxPanel extends JPanel {
    ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
    CheckboxPanel(){

        // Create checkboxes
        JCheckBox viewsCheckbox = new JCheckBox("Video views");
        JCheckBox likesCheckbox = new JCheckBox("Video likes");
        JCheckBox commentsCheckbox = new JCheckBox("Video comments");
        JCheckBox earningCheckbox = new JCheckBox("Money earning");
        checkboxes.add(viewsCheckbox);
        checkboxes.add(likesCheckbox);
        checkboxes.add(commentsCheckbox);
        checkboxes.add(earningCheckbox);
        setLayout(new GridLayout(4, 1));
        add(viewsCheckbox);
        add(likesCheckbox);
        add(commentsCheckbox);
        add(earningCheckbox);

    }
    public ArrayList<JCheckBox> getCheckboxes(){
        return checkboxes;
    }
}