package youtube.controlpanel.view.frames;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CheckboxPanel extends JPanel {
    ArrayList<JCheckBox> checkboxes = new ArrayList<>();

    CheckboxPanel() {
        // Create checkboxes
        JCheckBox viewsCheckbox = createStyledCheckbox("Video views");
        JCheckBox likesCheckbox = createStyledCheckbox("Video likes");
        JCheckBox commentsCheckbox = createStyledCheckbox("Video comments");
        JCheckBox earningCheckbox = createStyledCheckbox("Money earning");

        // Add checkboxes to the panel
        setLayout(new GridLayout(4, 1));
        add(viewsCheckbox);
        add(likesCheckbox);
        add(commentsCheckbox);
        add(earningCheckbox);
    }

    private JCheckBox createStyledCheckbox(String label) {
        JCheckBox checkbox = new JCheckBox(label);

        // Apply the desired style
        checkbox.setBackground(new Color(205, 32, 31)); // Same background as fetchButton
        checkbox.setForeground(Color.BLACK); // Same foreground as fetchButton
        checkbox.setFont(new Font("Arial", Font.PLAIN, 14)); // Same font as fetchButton
        checkbox.setFocusPainted(false);
        checkbox.setBorder(BorderFactory.createRaisedBevelBorder()); // Same border as fetchButton

        checkboxes.add(checkbox);
        return checkbox;
    }

    public ArrayList<JCheckBox> getCheckboxes() {
        return checkboxes;
    }
}
