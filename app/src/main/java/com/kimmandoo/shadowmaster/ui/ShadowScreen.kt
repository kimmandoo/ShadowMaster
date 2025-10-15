package com.kimmandoo.shadowmaster.ui

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.unaryMinus

@Composable
fun ShadowScreen(paddingValues: PaddingValues = PaddingValues()) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Elevation Shadow", "Drop Shadow")

    Column(
        modifier = Modifier.padding(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        )
    ) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(text = title) }
                )
            }
        }

        when (selectedTab) {
            0 -> ElevationShadowContent()
            1 -> DropShadowContent()
        }
    }
}

@Composable
fun ElevationShadowContent() {
    var elevation by remember { mutableStateOf(8f) }
    var cornerRadius by remember { mutableStateOf(16f) }
    var ambientAlpha by remember { mutableStateOf(0.1f) }
    var spotAlpha by remember { mutableStateOf(0.1f) }
    var red by remember { mutableStateOf(0f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }
    
    val shadowColor = Color(red = red, green = green, blue = blue)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(200.dp)
                    .shadow(
                        elevation = elevation.dp,
                        shape = RoundedCornerShape(cornerRadius.dp),
                        ambientColor = shadowColor.copy(alpha = ambientAlpha),
                        spotColor = shadowColor.copy(alpha = spotAlpha)
                    )
                    .clip(RoundedCornerShape(cornerRadius.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Text("Shadow Box")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            ControlSlider(
                label = "Elevation",
                value = elevation,
                onValueChange = { elevation = it },
                valueRange = 0f..40f,
                format = "%.0fdp"
            )
        }

        item {
            ControlSlider(
                label = "Corner Radius",
                value = cornerRadius,
                onValueChange = { cornerRadius = it },
                valueRange = 0f..100f,
                format = "%.0fdp"
            )
        }

        item {
            ControlSlider(
                label = "Ambient Alpha",
                value = ambientAlpha,
                onValueChange = { ambientAlpha = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }

        item {
            ControlSlider(
                label = "Spot Alpha",
                value = spotAlpha,
                onValueChange = { spotAlpha = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }

        item {
            ControlSlider(
                label = "Red",
                value = red,
                onValueChange = { red = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }

        item {
            ControlSlider(
                label = "Green",
                value = green,
                onValueChange = { green = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }

        item {
            ControlSlider(
                label = "Blue",
                value = blue,
                onValueChange = { blue = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }
    }
}

@Composable
fun DropShadowContent() {
    var cornerRadius by remember { mutableStateOf(16f) }
    var shadowAlpha by remember { mutableStateOf(0.25f) }
    var offsetX by remember { mutableStateOf(8f) }
    var offsetY by remember { mutableStateOf(8f) }
    var blurRadius by remember { mutableStateOf(20f) }
    var red by remember { mutableStateOf(0f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }
    
    val shadowColor = Color(red = red, green = green, blue = blue)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(200.dp)
                    .dropShadow(
                        color = shadowColor.copy(alpha = shadowAlpha),
                        offsetX = offsetX.dp,
                        offsetY = offsetY.dp,
                        blurRadius = blurRadius.dp,
                        shape = RoundedCornerShape(cornerRadius.dp)
                    )
                    .clip(RoundedCornerShape(cornerRadius.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Text("Shadow Box")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            ControlSlider(
                label = "Corner Radius",
                value = cornerRadius,
                onValueChange = { cornerRadius = it },
                valueRange = 0f..100f,
                format = "%.0fdp"
            )
        }

        item {
            ControlSlider(
                label = "Shadow Alpha",
                value = shadowAlpha,
                onValueChange = { shadowAlpha = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }

        item {
            ControlSlider(
                label = "Offset X",
                value = offsetX,
                onValueChange = { offsetX = it },
                valueRange = -40f..40f,
                format = "%.0fdp"
            )
        }

        item {
            ControlSlider(
                label = "Offset Y",
                value = offsetY,
                onValueChange = { offsetY = it },
                valueRange = -40f..40f,
                format = "%.0fdp"
            )
        }

        item {
            ControlSlider(
                label = "Blur Radius",
                value = blurRadius,
                onValueChange = { blurRadius = it },
                valueRange = 0f..100f,
                format = "%.0fdp"
            )
        }

        item {
            ControlSlider(
                label = "Red",
                value = red,
                onValueChange = { red = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }

        item {
            ControlSlider(
                label = "Green",
                value = green,
                onValueChange = { green = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }

        item {
            ControlSlider(
                label = "Blue",
                value = blue,
                onValueChange = { blue = it },
                valueRange = 0f..1f,
                format = "%.2f"
            )
        }
    }
}

@Composable
fun ControlSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    format: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "$label: ${format.format(value)}")
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

fun Modifier.dropShadow(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    shape: Shape
): Modifier {
   return drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            frameworkPaint.color = color.toArgb()

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()

            val path = Path().apply {
                addOutline(shape.createOutline(size, layoutDirection, this@drawBehind))
            }
            canvas.translate(leftPixel, topPixel)
            canvas.drawPath(path, paint)
            canvas.translate(-leftPixel, -topPixel)
        }
    }
}