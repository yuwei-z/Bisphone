package app.messaging.vh;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;

public class Call$$ViewInjector<T extends Call> extends MessageViewHolder$$ViewInjector<T> {
    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (MessageViewHolder) t, obj);
        t.f4036l = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755464, "field 'message'"), 2131755464, "field 'message'");
    }

    public void reset(T t) {
        super.reset((MessageViewHolder) t);
        t.f4036l = null;
    }
}
