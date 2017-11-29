package com.github.wuxudong.rncharts.charts;

import javax.annotation.Nullable;
import java.util.Map;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.wuxudong.rncharts.data.CandleDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class CandleStickChartManager extends BarLineChartBaseManager<CandleStickChart, CandleEntry> {

    public static final int COMMAND_ADD_ENTRY = 1;
    public static final int COMMAND_MOVE_VIEW_TO_X = 2;

    @Override
    public String getName() {
        return "RNCandleStickChart";
    }

    @Override
    protected CandleStickChart createViewInstance(ThemedReactContext reactContext) {
        CandleStickChart candleStickChart = new CandleStickChart(reactContext);
        candleStickChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(candleStickChart));
        return candleStickChart;
    }


    @Override
    DataExtract getDataExtract() {
        return new CandleDataExtract();
    }

    public @Nullable Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
            "addEntry", COMMAND_ADD_ENTRY,
            "moveViewToX", COMMAND_MOVE_VIEW_TO_X);
    }

    @Override
    public void receiveCommand(android.view.View chartView, int commandId, @Nullable ReadableArray args) {
        CandleStickChart chart = (CandleStickChart) chartView;
        switch (commandId) {
            case COMMAND_ADD_ENTRY:
                CandleDataExtract extract = new CandleDataExtract();
                CandleEntry entry = extract.createEntry(args, 0);

                chart.getData().addEntry(entry, 0);
                chart.notifyDataSetChanged();

                ViewPortHandler handler = chart.getViewPortHandler();

                float maxTransX = -handler.contentWidth() * (handler.getScaleX() - 1f);
                if (handler.getTransX() == maxTransX) {
                    chart.moveViewToX(entry.getX());
                }
                break;
            case COMMAND_MOVE_VIEW_TO_X:
                chart.moveViewToX((float)500.0);
                break;
        }
    }
}
