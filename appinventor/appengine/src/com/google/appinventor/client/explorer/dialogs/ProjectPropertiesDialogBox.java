package com.google.appinventor.client.explorer.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.appinventor.client.Ode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import com.google.appinventor.client.editor.simple.components.MockComponent;
import com.google.appinventor.client.editor.simple.components.MockForm;
import com.google.appinventor.client.editor.youngandroid.YaFormEditor;
import com.google.appinventor.client.editor.youngandroid.YaProjectEditor;
import com.google.appinventor.client.properties.Property;
import com.google.appinventor.client.widgets.properties.EditableProperties;
import com.google.appinventor.client.widgets.properties.EditableProperty;
import com.google.appinventor.client.widgets.properties.PropertyChangeListener;
import com.google.appinventor.client.widgets.properties.PropertyEditor;

public class ProjectPropertiesDialogBox extends DialogBox { 

    private static ProjectPropertiesDialogBoxUiBinder uiBinder =
        GWT.create(ProjectPropertiesDialogBoxUiBinder.class);
    private static ProjectPropertiesDialogBox lastDialog = null;

    interface ProjectPropertiesDialogBoxUiBinder extends UiBinder<Widget, ProjectPropertiesDialogBox> {
    }

    private static final HashSet<String> PROJECT_PROPERTIES = new HashSet<String>() {{
        add("Icon"); 
        add("VersionCode"); 
        add("VersionName"); 
        add("AppName");
        add("ShowListsAsJson"); 
        add("TutorialURL"); 
        add("BlocksToolkit"); 
        add("PrimaryColor");
        add("PrimaryColorDark");
        add("AccentColor");
        add("Theme");
        add("Sizing");
        add("DefaultFileScope");
    }};

    private MockForm form;

    private int count = 0;

    @UiField
    VerticalPanel panelLeftLabels;

    @UiField
    VerticalPanel panelLeftEditors;

    @UiField
    VerticalPanel panelRightLabels;

    @UiField
    VerticalPanel panelRightEditors;
    

    public ProjectPropertiesDialogBox() {
        this.setStylePrimaryName("ode-projectPropertyDialogDiv");
        add(uiBinder.createAndBindUi(this));
        this.center();
        this.setAnimationEnabled(true);
        this.setAutoHideEnabled(true);
        lastDialog = this;

        YaProjectEditor projectEditor = (YaProjectEditor) Ode.getInstance().getEditorManager().getOpenProjectEditor(
            Ode.getInstance().getCurrentYoungAndroidProjectId());
        String activeScreen = Ode.getInstance().getDesignToolbar().getCurrentProject().currentScreen;
        form = projectEditor.getFormFileEditor(activeScreen).getForm();

        EditableProperties ep = form.listProperties();
        Iterator<EditableProperty> properties = ep.iterator(); 

        while (properties.hasNext()) {
            EditableProperty p = properties.next();

            if (PROJECT_PROPERTIES.contains(p.getName())) {
                addProperty(p);
            }
        }
    }


    public static void closeIfOpen() {
        if (lastDialog != null) {
          lastDialog.removeFromParent();
          lastDialog = null;
        }
    }

    /**
     * Adds a new property to be displayed in the Dialog.
     *
     * @param property  new property to be shown
     */
    void addProperty(EditableProperty property) {
        Label label = new Label(property.getCaption()+" : ");
        PropertyEditor editor = property.getEditor();
        count++;
        // add the property to dialog

        if (count%2 == 1) {
            label.setStyleName("ode-propertyDialogLabelLeft");
            editor.setStyleName("ode-propertyDialogEditorLeft");
            panelLeftLabels.add(label);
            panelLeftEditors.add(editor);
        } else {
            label.setStyleName("ode-propertyDialogLabelRight");
            editor.setStyleName("ode-propertyDialogEditorRight");
            panelRightEditors.add(editor);
            panelRightLabels.add(label);
        }
        
    }

}
