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

import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.TextField;

import life.qbic.portal.Styles;

/**
 * Composite UI component of a TextField containing rollover text and other information
 * 
 * @author Andreas Friedrich
 * 
 */
public class OpenbisInfoTextField extends AOpenbisInfoComponent {

  private static final long serialVersionUID = -7892628867973563002L;

  public OpenbisInfoTextField(String label, String description) {
    super(description, new StandardTextField(label));
    inner.setStyleName(Styles.fieldTheme);

  }

  public OpenbisInfoTextField(String label, String description, String width) {
    super(description, new StandardTextField(label), width);
    inner.setStyleName(Styles.fieldTheme);
  }

  public OpenbisInfoTextField(String label, String description, String width, String value) {
    super(description, new StandardTextField(label, value), width);
    inner.setStyleName(Styles.fieldTheme);
  }

  public void setValue(String s) {
    ((TextField) inner).setValue(s);
  }

  public void setMaxLength(int max) {
    ((TextField) inner).setMaxLength(max);
  }

  public void setInputPrompt(String string) {
    ((AbstractTextField) inner).setPlaceholder(string);
  }

  @Override
  protected void doSetValue(Object o) {

  }
}
