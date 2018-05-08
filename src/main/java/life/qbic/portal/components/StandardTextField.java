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

import com.vaadin.ui.TextField;

public class StandardTextField extends TextField {

  /**
   * 
   */
  private static final long serialVersionUID = -7931461514790712645L;

  public StandardTextField(String caption) {
    super(caption);
    super.setHeight("31px");
  }

  public StandardTextField(String label, String value) {
    super(label, value);
    super.setHeight("31px");
  }

  public StandardTextField() {
    super();
    super.setHeight("31px");
  }

}
