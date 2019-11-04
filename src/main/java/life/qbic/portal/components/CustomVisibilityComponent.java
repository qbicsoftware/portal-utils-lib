/*******************************************************************************
 * QBiC Project Wizard enables users to create hierarchical experiments including different study conditions using factorial design.
 * Copyright (C) "2016"  Andreas Friedrich
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package life.qbic.portal.components;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;

import life.qbic.portal.controllers.VisibilityChangeListener;

public class CustomVisibilityComponent extends AbstractComponent {

  private static final long serialVersionUID = 7001531700669823055L;

private List<VisibilityChangeListener> listeners = new ArrayList<VisibilityChangeListener>();

  protected AbstractComponent inner;

  public CustomVisibilityComponent(String caption, AbstractComponent comp) {
    this.setCaption(caption);
    this.inner = comp;
  }

  public CustomVisibilityComponent(String caption, AbstractComponent comp, String width) {
    this(caption, comp);
    comp.setWidth(width);
  }

  public CustomVisibilityComponent(AbstractComponent comp) {
    this.inner = comp;
  }

//  public Class<String> getType() {
//    return String.class;
//  }

  public AbstractComponent getInnerComponent() {
    return inner;
  }

  public void setEnabled(boolean b) {
    inner.setEnabled(b);
  }

  public void setSize(String width, String height) {
    inner.setHeight(height);
    inner.setWidth(width);
  }

  public String getValue() {
    if (inner instanceof AbstractField)
      return (String) ((AbstractField) inner).getValue();
    else
      return null;
  }


  public void addListener(VisibilityChangeListener toAdd) {
    listeners.add(toAdd);
  }

  @Override
  public void setVisible(boolean b) {
    inner.setVisible(b);
    // Notify everybody that may be interested.
    for (VisibilityChangeListener hl : listeners)
      hl.setVisible(b);
  }

  @Deprecated
  public void setNullSelectionAllowed(boolean b) {
  }

  @Deprecated
  public void setItemEnabled(String string, boolean b) {
  }

  @Deprecated
  public Object getNullSelectionItemId() {
    return null;
  }

  @Deprecated
  public void select(Object item) {
  }
}
