package com.knw.myjetchat.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.knw.myjetchat.MainActivity;
import com.knw.myjetchat.R;

import java.util.ArrayList;
import java.util.List;

public class AtBottomSheetDialogFragment extends BottomSheetDialogFragment  implements AtAdapter.onAtClickListener {
    private static  ArrayList<String> atlist;
    public static AtBottomSheetDialogFragment newInstance(ArrayList<String> dataList) {
        AtBottomSheetDialogFragment fragment = new AtBottomSheetDialogFragment();
        atlist=dataList;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.at_bottom_sheet_dialog, container, false);
        RecyclerView atRecyclerView =view.findViewById(R.id.atlists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        atRecyclerView.setLayoutManager(layoutManager);
        AtAdapter adapter = new AtAdapter(this.atlist,this);
        atRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAtClick(String at) {
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addAtToEditText(at);
            //关闭当前fragment
            getParentFragmentManager().beginTransaction().remove(this).commit();

        }

    }
}
