package com.example.looking.ui.example.view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.looking.R;
import com.example.looking.base.BaseFragment;
import com.example.looking.ui.example.IExampleContract;
import com.example.looking.ui.example.presenter.ExamplePresenter;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.LinearRing;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.geometry.geo.Projection;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PatternRepeatMode;
import com.yandex.mapkit.map.PolygonMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.resource_url_provider.DefaultUrlProvider;
import com.yandex.mapkit.tiles.UrlProvider;
import com.yandex.runtime.image.AnimatedImageProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ExampleFragment extends BaseFragment<IExampleContract.Presenter> implements IExampleContract.View {

    private final String MAPKIT_API_KEY = "bb33f992-70c6-4137-a77d-d0b21f4f07ed";private final Point CAMERA_TARGET = new Point(59.952, 30.318);
    private final Point ANIMATED_RECTANGLE_CENTER = new Point(59.956, 30.313);
    private final Point TRIANGLE_CENTER = new Point(34.101075, 44.949581);
    private final Point POLYLINE_CENTER = CAMERA_TARGET;
    private final Point CIRCLE_CENTER = new Point(59.956, 30.323);
    private final Point DRAGGABLE_PLACEMARK_CENTER = new Point(44.949581,34.101075 );
    private final double OBJECT_SIZE = 0.013;
    @BindView(R.id.mapview)
    MapView mapView;


    private UrlProvider urlProvider;
    private DefaultUrlProvider resourceUrlProvider;
    private Projection projection;
    private MapObjectCollection mapObjects;
    private Handler animationHandler;

    @Override
    protected IExampleContract.Presenter getNewPresenter() {
        return new ExamplePresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        MapKitFactory.initialize(getBaseActivity());
        super.onViewCreated(view, savedInstanceState);
        mapView.getMap().move(
                new CameraPosition(TRIANGLE_CENTER, 15.0f, 0.0f, 0.0f));
        mapObjects = mapView.getMap().getMapObjects().addCollection();
        animationHandler = new Handler();
        createMapObjects();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public void initView(int stringId) {
//        tvExample.setText(stringId);
    }

    @Override
    protected int getLayout() {
        return R.layout.framgent_example;
    }


    private void createMapObjects() {
        AnimatedImageProvider animatedImage = AnimatedImageProvider.fromAsset(getBaseActivity(), "animation.png");
        ArrayList<Point> rectPoints = new ArrayList<>();
        rectPoints.add(new Point(
                ANIMATED_RECTANGLE_CENTER.getLatitude() - OBJECT_SIZE,
                ANIMATED_RECTANGLE_CENTER.getLongitude() - OBJECT_SIZE));
        rectPoints.add(new Point(
                ANIMATED_RECTANGLE_CENTER.getLatitude() - OBJECT_SIZE,
                ANIMATED_RECTANGLE_CENTER.getLongitude() + OBJECT_SIZE));
        rectPoints.add(new Point(
                ANIMATED_RECTANGLE_CENTER.getLatitude() + OBJECT_SIZE,
                ANIMATED_RECTANGLE_CENTER.getLongitude() + OBJECT_SIZE));
        rectPoints.add(new Point(
                ANIMATED_RECTANGLE_CENTER.getLatitude() + OBJECT_SIZE,
                ANIMATED_RECTANGLE_CENTER.getLongitude() - OBJECT_SIZE));
        PolygonMapObject rect = mapObjects.addPolygon(
                new Polygon(new LinearRing(rectPoints), new ArrayList<LinearRing>()));
        rect.setStrokeColor(getResources().getColor(R.color.colorAccent));
        rect.setFillColor(getResources().getColor(R.color.colorPrimary));
        rect.setAnimatedImage(animatedImage, 32.0f, PatternRepeatMode.REPEAT);

        for(int j = -20; j < 20; j++) {
            for (int i = -20; i < 20; i++) {
                ArrayList<Point> trianglePoints = new ArrayList<>();
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + OBJECT_SIZE * i,
                        TRIANGLE_CENTER.getLongitude()+ OBJECT_SIZE * j));
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + OBJECT_SIZE + OBJECT_SIZE * i,
                        TRIANGLE_CENTER.getLongitude()+ OBJECT_SIZE * j));
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + OBJECT_SIZE + OBJECT_SIZE * i,
                        TRIANGLE_CENTER.getLongitude() + OBJECT_SIZE+ OBJECT_SIZE * j));
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + OBJECT_SIZE * i,
                        TRIANGLE_CENTER.getLongitude() + OBJECT_SIZE+ OBJECT_SIZE * j));
                PolygonMapObject triangle = mapObjects.addPolygon(
                        new Polygon(new LinearRing(trianglePoints), new ArrayList<LinearRing>()));
                switch (i*j%10) {
                    case 0:
                        triangle.setFillColor(getResources().getColor(R.color.color0));
                        break;
                    case 1:
                        triangle.setFillColor(getResources().getColor(R.color.color1));
                        break;
                    case 2:
                        triangle.setFillColor(getResources().getColor(R.color.color2));
                        break;
                    case 3:
                        triangle.setFillColor(getResources().getColor(R.color.color3));
                        break;
                    case 4:
                        triangle.setFillColor(getResources().getColor(R.color.color4));
                        break;
                    case 5:
                        triangle.setFillColor(getResources().getColor(R.color.color5));
                        break;
                    case 6:
                        triangle.setFillColor(getResources().getColor(R.color.color6));
                        break;
                    case 7:
                        triangle.setFillColor(getResources().getColor(R.color.color7));
                        break;
                    case 8:
                        triangle.setFillColor(getResources().getColor(R.color.color8));
                        break;
                    case 9:
                        triangle.setFillColor(getResources().getColor(R.color.color9));
                        break;
                }
                triangle.setStrokeColor(Color.TRANSPARENT);
                triangle.setStrokeWidth(0.0f);
                triangle.setZIndex(100.0f);
            }
        }

//        CircleMapObject circle = mapObjects.addCircle(
//                new Circle(CIRCLE_CENTER, 100), getResources().getColor(R.color.colorAccent), 2, getResources().getColor(R.color.colorPrimaryDark));
//        circle.setZIndex(100.0f);
//
//        ArrayList<Point> polylinePoints = new ArrayList<>();
//        polylinePoints.add(new Point(
//                POLYLINE_CENTER.getLatitude() + OBJECT_SIZE,
//                POLYLINE_CENTER.getLongitude()- OBJECT_SIZE));
//        polylinePoints.add(new Point(
//                POLYLINE_CENTER.getLatitude() - OBJECT_SIZE,
//                POLYLINE_CENTER.getLongitude()- OBJECT_SIZE));
//        polylinePoints.add(new Point(
//                POLYLINE_CENTER.getLatitude(),
//                POLYLINE_CENTER.getLongitude() + OBJECT_SIZE));
//
//        PolylineMapObject polyline = mapObjects.addPolyline(new Polyline(polylinePoints));
//        polyline.setStrokeColor(Color.BLACK);
//        polyline.setZIndex(100.0f);
//
//        PlacemarkMapObject mark = mapObjects.addPlacemark(DRAGGABLE_PLACEMARK_CENTER);
//        mark.setOpacity(0.5f);
//        mark.setIcon(ImageProvider.fromResource(this, R.drawable.mark));
//        mark.setDraggable(true);
//
//        createPlacemarkMapObjectWithViewProvider();
    }
}
