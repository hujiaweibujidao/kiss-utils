package com.javayhu.kiss.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.javayhu.kiss.R;
import com.javayhu.kiss.activity.FragmentActivity;
import com.javayhu.kiss.utils.FragmentUtils;
import com.javayhu.kiss.utils.ToastUtils;

import java.util.Random;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 17/02/02
 *     desc  :
 * </pre>
 */
public class Demo3Fragment extends Fragment
        implements View.OnClickListener {

    private Fragment fragment1;

    public static Demo3Fragment newInstance() {

        Bundle args = new Bundle();

        Demo3Fragment fragment = new Demo3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Button   btnShowAboutFragment;
    private TextView tvAboutFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demo0, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnShowAboutFragment = (Button) view.findViewById(R.id.btn_show_about_fragment);
        btnShowAboutFragment.setOnClickListener(this);
        view.findViewById(R.id.btn_add_show).setOnClickListener(this);
        view.findViewById(R.id.btn_add_child).setOnClickListener(this);
        view.findViewById(R.id.btn_pop_to_root).setOnClickListener(this);
        view.findViewById(R.id.btn_pop_add).setOnClickListener(this);
        view.findViewById(R.id.btn_hide_show).setOnClickListener(this);
        view.findViewById(R.id.btn_replace).setOnClickListener(this);
        tvAboutFragment = (TextView) view.findViewById(R.id.tv_about_fragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Random random = new Random();
        FragmentUtils.setBackgroundColor(this, Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        tvAboutFragment.setText("");
        switch (view.getId()) {
            case R.id.btn_show_about_fragment:
                tvAboutFragment.setText("---stack top---\n"
                        + FragmentUtils.getAllFragments(getFragmentManager()).toString()
                        + "\n---stack bottom---\n"
                        + "\ntopFragment: " + FragmentUtils.getLastAddFragment(getFragmentManager()).getClass().getSimpleName()
                        + "\ntopShowFragment: " + FragmentUtils.getTopShowFragment(getFragmentManager()).getClass().getSimpleName()
                );
                break;
            case R.id.btn_add_show:
                FragmentUtils.addFragment(getFragmentManager(), Demo1Fragment.newInstance(), R.id.fragment_container, true, true);
                break;
            case R.id.btn_add_child:
                FragmentUtils.addFragment(getChildFragmentManager(), Demo2Fragment.newInstance(), R.id.child_fragment_container, false, true);
                break;
            case R.id.btn_pop_to_root:
                FragmentUtils.popToFragment(getFragmentManager(), Demo1Fragment.class, true);
                break;
            case R.id.btn_pop_add:
                ViewCompat.setTransitionName(btnShowAboutFragment, "addSharedElement");
                FragmentUtils.popAddFragment(getFragmentManager(), R.id.fragment_container, Demo2Fragment.newInstance(), true, new FragmentUtils.SharedElement(this.btnShowAboutFragment, "btnShowAboutFragment"));
                break;
            case R.id.btn_hide_show:
                Fragment fragment1 = FragmentUtils.findFragment(getFragmentManager(), Demo1Fragment.class);
                if (fragment1 != null) {
                    FragmentUtils.hideShowFragment(this, fragment1);
                } else {
                    ToastUtils.showLongToast("please add demo1 first!");
                }
                break;
            case R.id.btn_replace:
                ((FragmentActivity) getActivity()).rootFragment = FragmentUtils.replaceFragment(this, Demo0Fragment.newInstance(), false);
                break;
        }
    }
}
