package samtech.org.bussinesssample.Utils

import android.graphics.drawable.Drawable
import android.location.Location
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

object MapUtils {
    fun setMap(
        paramMap: MapView, hasZoom: Boolean, hasMultiTouch: Boolean,
        paramZoom: Double, paramLatLgn: Location,
        paramIcon: Drawable?, paramMTitle: String?
    ) {
        paramMap.setHasTransientState(true)
        paramMap.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        paramMap.zoomController.setVisibility(if (hasZoom) CustomZoomButtonsController.Visibility.ALWAYS else CustomZoomButtonsController.Visibility.NEVER)
        paramMap.setMultiTouchControls(hasMultiTouch)
        val mapController = paramMap.controller
        mapController.setZoom(paramZoom)
        val startPoint = GeoPoint(
            paramLatLgn.latitude,
            paramLatLgn.longitude
        )
        mapController.setCenter(startPoint)
        val startMarker = Marker(paramMap)
        startMarker.title = paramMTitle
        startMarker.position = startPoint
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        paramMap.overlays.add(startMarker)
        startMarker.icon = paramIcon
    }
}