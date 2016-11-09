package search.quangnhat.com.search;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Me on 11/8/2016.
 */

public class HintAdapter extends ArrayAdapter<String>{
    public HintAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
