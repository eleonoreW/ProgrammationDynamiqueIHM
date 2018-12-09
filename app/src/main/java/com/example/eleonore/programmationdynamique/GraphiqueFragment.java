package com.example.eleonore.programmationdynamique;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eleonore.programmationdynamique.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphiqueFragment extends Fragment {
    Turbine turbine1,turbine2,turbine3,turbine4,turbine5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.initSimulation(turbine1,turbine2,turbine3,turbine4,turbine5);



        // Inflate the layout for this fragment
        GraphView graph = (GraphView) getView().findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);


        return inflater.inflate(R.layout.graphique_fragment, container, false);
    }
}