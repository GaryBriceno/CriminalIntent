package pe.net.lambda.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Gary on 21/05/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
