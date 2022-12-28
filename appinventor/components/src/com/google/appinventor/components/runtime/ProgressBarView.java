package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;

import android.widget.ProgressBar;
import android.view.View;

/**
 * `ProgressBarView` are used as loading indicators in android applications. 
 * These are generally used when the application is loading the data from the server or database.
 */
@DesignerComponent(version = YaVersion.PROGRESSBAR_COMPONENT_VERSION,
    description = "<p>A ProgressBar are used as loading indicators in android applications." +
        " These are generally used when the application is loading the data from the server or database." +
        " Progress bar in android is useful since it gives the user an idea of time to finish its task. </p>",
    category = ComponentCategory.USERINTERFACE)
@SimpleObject
public final class ProgressBarView extends AndroidViewComponent {

    private final android.widget.ProgressBar view;

    public boolean visible;

    public ProgressBarView(ComponentContainer container) {
        super(container);
        view = new ProgressBar(container.$context());

        container.$add(this);
    }

    @Override
    public View getView() {
      return view;
    }
}
