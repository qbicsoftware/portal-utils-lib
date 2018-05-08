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

import com.vaadin.ui.AbstractField;

/**
 * Abstract UI component of a Component containing rollover text and other information
 * 
 * @author Andreas Friedrich
 * 
 */
abstract public class AOpenbisInfoComponent extends AbstractField<Object> {

  private static final long serialVersionUID = 377809399988492626L;
  protected AbstractField<?> inner;

  public AOpenbisInfoComponent(String description, AbstractField<?> comp) {
    this.setDescription(description);
    this.inner = comp;
    this.inner.setDescription(description);
  }

  public AOpenbisInfoComponent(String description, AbstractField<?> comp, String width) {
    this(description, comp);
    comp.setWidth(width);
  }

  @Override
  public Class<String> getType() {
    return String.class;
  }

  public AbstractField<?> getInnerComponent() {
    return inner;
  }

  public void setVisible(boolean b) {
    inner.setVisible(b);
  }

  public void setEnabled(boolean b) {
    inner.setEnabled(b);
  }

  public void setSize(String width, String height) {
    inner.setHeight(height);
    inner.setWidth(width);
  }

  public String getValue() {
    return (String) inner.getValue();
  }
}
