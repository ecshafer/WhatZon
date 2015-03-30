// Generated code from Butter Knife. Do not modify!
package suny.com.softwareeng;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class Events$$ViewInjector<T extends suny.com.softwareeng.Events> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427487, "field 'flingContainer'");
    target.flingContainer = finder.castView(view, 2131427487, "field 'flingContainer'");
    view = finder.findRequiredView(source, 2131427386, "method 'right'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.right();
        }
      });
    view = finder.findRequiredView(source, 2131427385, "method 'left'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.left();
        }
      });
  }

  @Override public void reset(T target) {
    target.flingContainer = null;
  }
}
