package com.example.sharedviewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment implements MovieAdapter.OnItemCLickListener{

    public MasterFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private NavController navController;
    private SharedViewModel sharedViewModel;
    private List<Movie> movieList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(movieList, this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMovieList().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieList.clear();
                movieList.addAll(movies);
                movieAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
//        Toast.makeText(requireContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        sharedViewModel.setMovie(position);
        navController.navigate(R.id.action_masterFragment_to_detailFragment);
    }
}











