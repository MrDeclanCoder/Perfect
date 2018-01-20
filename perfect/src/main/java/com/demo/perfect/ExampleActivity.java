package com.demo.perfect;

import com.dch.perfect.core.activities.ProxyActivity;
import com.dch.perfect.core.delegates.PerfectDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public PerfectDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
