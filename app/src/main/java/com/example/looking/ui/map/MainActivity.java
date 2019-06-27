package com.example.looking.ui.map;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.looking.R;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.LinearRing;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.geometry.geo.Projection;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PatternRepeatMode;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.PolygonMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.resource_url_provider.DefaultUrlProvider;
import com.yandex.mapkit.tiles.UrlProvider;
import com.yandex.runtime.image.AnimatedImageProvider;
import com.yandex.runtime.ui_view.ViewProvider;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    /**
     * Replace "your_api_key" with a valid developer key.
     * You can get it at the https://developer.tech.yandex.ru/ website.
     */
    private final String MAPKIT_API_KEY = "bb33f992-70c6-4137-a77d-d0b21f4f07ed";
    private final Point CAMERA_TARGET = new Point(59.952, 30.318);
    private final Point ANIMATED_RECTANGLE_CENTER = new Point(59.956, 30.313);
    private final Point TRIANGLE_CENTER = new Point(34.101075, 44.949581);
    private final Point POLYLINE_CENTER = CAMERA_TARGET;
    private final Point CIRCLE_CENTER = new Point(59.956, 30.323);
    private final Point DRAGGABLE_PLACEMARK_CENTER = new Point(44.949581, 34.101075);
    private final double OBJECT_SIZE = 0.013;

    private UrlProvider urlProvider;
    private DefaultUrlProvider resourceUrlProvider;
    private Projection projection;
    private MapObjectCollection mapObjects;
    private Handler animationHandler;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        setContentView(R.layout.custom_layer);
        super.onCreate(savedInstanceState);
//
//        urlProvider = new UrlProvider() {
//            @Override
//            public String formatUrl(TileId tileId, Version version) {
//                return "https://maps-ios-pods-public.s3.yandex.net/mapkit_logo.png";
//            }
//        };
//        resourceUrlProvider = new DefaultUrlProvider();
//        projection = Projections.createWgs84Mercator();

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.getMap().move(
                new CameraPosition(TRIANGLE_CENTER, 15.0f, 0.0f, 0.0f));
        mapObjects = mapView.getMap().getMapObjects().addCollection();
        animationHandler = new Handler();
        createMapObjects();
//        Layer l = mapView.getMap().addLayer(
//                "mapkit_logo",
//                "image/png",
//                new LayerOptions(),
//                urlProvider,
//                resourceUrlProvider,
//                projection);
//        l.invalidate("0.0.0");
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    private void createMapObjects() {
        AnimatedImageProvider animatedImage = AnimatedImageProvider.fromAsset(this, "animation.png");
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

        double b = (OBJECT_SIZE * Math.sin(45))/Math.sin(90);
        double c = (OBJECT_SIZE * Math.sin(45))/Math.sin(90);
        double maxWIth0 = OBJECT_SIZE + b*2;
        double maxHeight0 = c*2;
        double maxWIth1 = OBJECT_SIZE + b*2;
        double maxHeight1 = c*2;

        for (int j = -10; j < 10; j++) {
            for (int i = -20; i < 20; i++) {
                ArrayList<Point> trianglePoints = new ArrayList<>();
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude()+ maxHeight * j ,
                        TRIANGLE_CENTER.getLongitude() + maxWIth * i));
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + maxHeight * j - c ,
                        TRIANGLE_CENTER.getLongitude()+ maxWIth * i + b));
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + maxHeight * j - c,
                        TRIANGLE_CENTER.getLongitude() + maxWIth * i + b + OBJECT_SIZE));
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + maxHeight * j,
                        TRIANGLE_CENTER.getLongitude() + maxWIth * i + b + OBJECT_SIZE + b));
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + maxHeight * j + c,
                        TRIANGLE_CENTER.getLongitude() + maxWIth * i+ b + OBJECT_SIZE));
                trianglePoints.add(new Point(
                        TRIANGLE_CENTER.getLatitude() + maxHeight * j + c,
                        TRIANGLE_CENTER.getLongitude() + maxWIth * i + b));

                PolygonMapObject triangle = mapObjects.addPolygon(
                        new Polygon(new LinearRing(trianglePoints), new ArrayList<LinearRing>()));
                switch (i * j % 10) {
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

    private void createPlacemarkMapObjectWithViewProvider() {
        final TextView textView = new TextView(this);
        final int[] colors = new int[]{Color.RED, Color.GREEN, Color.BLACK};
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);

        textView.setTextColor(Color.RED);
        textView.setText("Hello, World!");

        final ViewProvider viewProvider = new ViewProvider(textView);
        final PlacemarkMapObject viewPlacemark =
                mapObjects.addPlacemark(new Point(59.946263, 30.315181), viewProvider);

        final Random random = new Random();
        final int delayToShowInitialText = 5000;  // milliseconds
        final int delayToShowRandomText = 500; // milliseconds;

        // Show initial text `delayToShowInitialText` milliseconds and then
        // randomly change text in textView every `delayToShowRandomText` milliseconds
        animationHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final int randomInt = random.nextInt(1000);
                textView.setText("Some text version " + randomInt);
                textView.setTextColor(colors[randomInt % colors.length]);
                viewProvider.snapshot();
                viewPlacemark.setView(viewProvider);
                animationHandler.postDelayed(this, delayToShowRandomText);
            }
        }, delayToShowInitialText);
    }
}
