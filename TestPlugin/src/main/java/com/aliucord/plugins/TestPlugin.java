package com.aliucord.plugins;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.*;
import android.os.*;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.aliucord.Constants;
import com.aliucord.Utils;
import com.aliucord.entities.Plugin;
import com.aliucord.patcher.PinePatchFn;
import com.aliucord.plugins.testplugin.*;
import com.discord.utilities.color.ColorCompat;
import com.discord.api.premium.PremiumTier;
import com.discord.databinding.WidgetChatOverlayBinding;
import com.discord.stores.StoreStream;
import com.discord.widgets.chat.*;
import com.discord.widgets.chat.input.*;
import com.discord.widgets.chat.overlay.WidgetChatOverlay$binding$2;
import com.lytefast.flexinput.*;

import java.util.*;

public class TestPlugin extends Plugin {

    public TestPlugin() {
        settingsTab = new SettingsTab(PluginSettings.class).withArgs(settings);
        needsResources = true;
    }
    
    private Drawable pluginIcon;
    public RelativeLayout overlay;

    @NonNull
  @Override
  public Manifest getManifest() {
    var manifest = new Manifest();
    manifest.authors =
      new Manifest.Author[] {
        new Manifest.Author("Wing", 298295889720770563L),
      };
    manifest.description = "Used for testing";
    manifest.version = "1.0.0";
    manifest.updateUrl =
      "https://raw.githubusercontent.com/wingio/plugins/builds/updater.json";
    return manifest;
  }

    @Override
    public void start(Context context) throws Throwable {
        pluginIcon = ResourcesCompat.getDrawable(resources, resources.getIdentifier("ic_editfriend", "drawable", "com.aliucord.plugins"), null );
        var id = View.generateViewId();
        
        patcher.patch(WidgetUrlActions.class, "onViewCreated", new Class<?>[] { View.class, Bundle.class }, new PinePatchFn(callFrame -> {
            LinearLayout view = (LinearLayout) callFrame.args[0];
            var option = new TextView(view.getContext(), null, 0, R$h.UiKit_Settings_Item_Icon);
            option.setText("Open in External Browser");
            option.setId(id);
            if (pluginIcon != null) pluginIcon.setTint(ColorCompat.getThemedColor(view.getContext(), R$b.colorInteractiveNormal));
            option.setCompoundDrawablesRelativeWithIntrinsicBounds(pluginIcon,null,null,null);
            option.setOnClickListener(e -> {
                String body = view.getContext.getString(2131887249);
                Utils.log(body);
            });
            view.addView(option, 4);
        }));
    }

    @Override
    public void stop(Context context) { patcher.unpatchAll(); }
}
