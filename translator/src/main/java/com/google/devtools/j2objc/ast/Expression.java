/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.j2objc.ast;

import com.google.devtools.j2objc.javac.BindingConverter;

import org.eclipse.jdt.core.dom.ITypeBinding;

import javax.lang.model.type.TypeMirror;

/**
 * Base node class for all expressions.
 */
public abstract class Expression extends TreeNode {

  private Object constantValue;

  // TODO(tball): remove when all subclasses are converted.
  protected Expression(org.eclipse.jdt.core.dom.Expression jdtNode) {
    super(jdtNode);
    constantValue = jdtNode.resolveConstantExpressionValue();
  }

  protected Expression(Expression other) {
    super(other);
    constantValue = other.getConstantValue();
  }

  protected Expression() {
    super();
    constantValue = null;
  }

  public ITypeBinding getTypeBinding() {
    return BindingConverter.unwrapTypeMirrorIntoTypeBinding(getTypeMirror());
  }

  public abstract TypeMirror getTypeMirror();

  public Object getConstantValue() {
    return constantValue;
  }

  Expression setConstantValue(Object value) {
    constantValue = value;
    return this;
  }

  @Override
  public abstract Expression copy();
}
