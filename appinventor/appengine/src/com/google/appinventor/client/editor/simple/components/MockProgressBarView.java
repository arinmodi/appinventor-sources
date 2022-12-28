package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.gwt.user.client.ui.SimplePanel;

public class MockProgressBarView extends MockVisibleComponent {

    public static final String TYPE = "ProgressBarView";

    private final SimplePanel progressBarWidget;

    public MockProgressBarView(SimpleEditor editor){
      super(editor, TYPE, images.progressbar());

      progressBarWidget = new SimplePanel();
      progressBarWidget.setStylePrimaryName("ode-SimpleMockComponent");
      progressBarWidget.setWidget(getIconImage());
      initComponent(progressBarWidget);
    }

    @Override
    public int getPreferredWidth() {
      // The superclass uses getOffsetWidth, which won't work for us.
      return ComponentConstants.PROGRESSBAR_PREFERRED_WIDTH;
    }
  
    @Override
    public int getPreferredHeight() {
      // The superclass uses getOffsetHeight, which won't work for us.
      return ComponentConstants.PROGRESSBAR_PREFERRED_HEIGHT;
    }
}
