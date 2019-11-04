package life.qbic.portal;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.PopupView.Content;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import life.qbic.portal.components.CustomVisibilityComponent;
import life.qbic.portal.controllers.VisibilityChangeListener;

public class Styles {
  public enum NotificationType {
    ERROR, SUCCESS, DEFAULT
  }

  public static void notification(String title, String description, NotificationType type) {
    Notification notify = new Notification(title, description);
    notify.setPosition(Position.TOP_CENTER);
    switch (type) {
      case ERROR:
        notify.setDelayMsec(16000);
        notify.setIcon(FontAwesome.FROWN_O);
        notify.setStyleName(ValoTheme.NOTIFICATION_ERROR + " " + ValoTheme.NOTIFICATION_CLOSABLE);
        break;
      case SUCCESS:
        notify.setDelayMsec(8000);
        notify.setIcon(FontAwesome.SMILE_O);
        notify.setStyleName(ValoTheme.NOTIFICATION_SUCCESS + " " + ValoTheme.NOTIFICATION_CLOSABLE);
        break;
      default:
        notify.setDelayMsec(8000);
        notify.setIcon(FontAwesome.COMMENT);
        notify.setStyleName(ValoTheme.NOTIFICATION_TRAY + " " + ValoTheme.NOTIFICATION_CLOSABLE);
        break;
    }
    notify.show(Page.getCurrent());
  }

  public static String boxTheme = ValoTheme.COMBOBOX_SMALL;
  public static String fieldTheme = ValoTheme.TEXTFIELD_SMALL;
  public static String areaTheme = ValoTheme.TEXTAREA_SMALL;
  public static String tableTheme = ValoTheme.TABLE_SMALL;

  public static void iconButton(Button b, Resource icon) {
    b.setStyleName(ValoTheme.BUTTON_BORDERLESS);
    b.setIcon(icon);
    b.setWidth("10px");
  }


  public static PopupView getPopupViewContaining(Component c) {
    PopupView pv = new PopupView(new Content() {

      @Override
      public Component getPopupComponent() {
        VerticalLayout v = new VerticalLayout(c);
        v.setMargin(true);
        return v;
      }

      @Override
      public String getMinimizedValueAsHTML() {
        return "[?]";
      }
    });
    pv.setHideOnMouseOut(false);
    return pv;
  }

  public static HorizontalLayout questionize(Component c, final String info, final String header) {
    final HorizontalLayout res = new HorizontalLayout();
    res.setSpacing(true);
    if (c instanceof CustomVisibilityComponent) {
      CustomVisibilityComponent custom = (CustomVisibilityComponent) c;
      c = custom.getInnerComponent();
      custom.addListener(new VisibilityChangeListener() {

        @Override
        public void setVisible(boolean b) {
          res.setVisible(b);
        }
      });
    }

    res.setVisible(c.isVisible());
    res.setCaption(c.getCaption());
    c.setCaption(null);
    res.addComponent(c);

    PopupView pv = new PopupView(new Content() {

      @Override
      public Component getPopupComponent() {
        Label l = new Label(info, ContentMode.HTML);
        l.setCaption(header);
        l.setIcon(FontAwesome.INFO);
        l.setWidth("350px");
        l.addStyleName("info");
        return new VerticalLayout(l);
      }

      @Override
      public String getMinimizedValueAsHTML() {
        return "[?]";
      }
    });
    pv.setHideOnMouseOut(false);

    res.addComponent(pv);

    return res;
  }
}
