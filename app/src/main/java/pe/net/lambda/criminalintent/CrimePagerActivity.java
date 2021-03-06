package pe.net.lambda.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

import pe.net.lambda.criminalintent.modelo.Crime;
import pe.net.lambda.criminalintent.modelo.CrimeLab;

/**
 * Created by Gary on 22/05/2017.
 */

public class CrimePagerActivity extends AppCompatActivity
                                implements CrimeFragment.Callbacks{

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private static final String EXTRA_CRIME_ID = "pe.net.lambda.criminalintent.crime_id";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager)findViewById(R.id.activity_crime_pager_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(
                new FragmentStatePagerAdapter(fragmentManager) {
                    @Override
                    public Fragment getItem(int position) {
                        Crime crime = mCrimes.get(position);
                        return CrimeFragment.newInstance(crime.getId());
                    }

                    @Override
                    public int getCount() {
                        return mCrimes.size();
                    }
                }
        );

        for (int i=0; i<mCrimes.size(); i++){
            if( mCrimes.get(i).getId().equals(crimeId) ){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContent, UUID crimeId){
        Intent intent = new Intent( packageContent, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public void onCrimeUpdate(Crime crime) {
        /* Se debe implementar CrimeFragment.Callbacks en todas las activity que alberguen CrimeFragment
        por lo cual agregamos una implementación vacía en esta clase */
    }
}
