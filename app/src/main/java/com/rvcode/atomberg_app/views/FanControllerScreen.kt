package com.rvcode.atomberg_app.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rvcode.atomberg_app.R
import com.rvcode.atomberg_app.viewModelFactoroies.FanControllerViewModelFactory
import com.rvcode.atomberg_app.viewmodels.FanControllerViewModel
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


val DeepBlueBg = Color(0xFF021024)
val GlowBlue = Color(0xFF007BFF)
val NeonCyan = Color(0xFF4FC3F7)
val TextWhite = Color(0xFFEEEEEE)

@Composable
fun FanControllerScreen(navHostController: NavHostController, deviceId: String?) {

    val fanViewModel : FanControllerViewModel = viewModel(
        factory = FanControllerViewModelFactory()
    )

    val deviceStateList by fanViewModel.deviceStateList.observeAsState()

    var speed by remember { mutableIntStateOf(0) }
    val isLight = remember { mutableStateOf(false) }
    val isSleep = remember { mutableStateOf(false) }
    val timeHour = remember { mutableStateOf(0) }

    LaunchedEffect(deviceStateList) {
        deviceStateList?.let { states ->
            // Find the specific device matching the passed deviceId
            val currentDevice = states.find { it.deviceId == deviceId }

            currentDevice?.let { device ->
                isLight.value = device.led
                isSleep.value = device.sleepMode
                timeHour.value = device.timerHours
                speed = device.lastRecordedSpeed
            }
        }
    }

    var lightState: String = if(isLight.value) "ON" else "OFF"
    var sleepState : String  = if (isSleep.value) "ON" else "OFF"
    var timerState : String = if(timeHour.value==0) "OFF" else timeHour.value.toString()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF053f7e), DeepBlueBg),
                    center = Offset.Unspecified,
                    radius = 1000f
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // 2. Pass state and callback to the Dial
            SpeedControlDial(
                fanViewModel,
                deviceId,
                currentSpeed = speed,
                onSpeedChange = { newSpeed -> speed = newSpeed }
            )

            Spacer(modifier = Modifier.weight(1f))

            BottomControlRow(fanControllerViewModel = fanViewModel, deviceId, timeHour , isSleep= isSleep, isLight = isLight, timerState = timerState, lightState = lightState, sleepState = sleepState)

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SpeedControlDial(
    fanControllerViewModel: FanControllerViewModel,
    deviceId: String?,
    currentSpeed: Int,
    onSpeedChange: (Int) -> Unit
) {
    // 1. Angles for Speeds 1 to 6 (Top Half)

    val speedAngles = remember { listOf(180f, 216f, 252f, 288f, 324f, 360f) }

    // Helper to calculate nearest speed based on touch angle
    fun getSpeedFromAngle(touchAngle: Float): Int {
        var closestDistance = Float.MAX_VALUE
        var closestIndex = 0

        speedAngles.forEachIndexed { index, angle ->
            val diff = kotlin.math.abs(angle - touchAngle)
            if (diff < closestDistance) {
                closestDistance = diff
                closestIndex = index
            }
        }
        val speed = if (closestDistance < 25f) closestIndex + 1 else currentSpeed
        deviceId?.let {
            fanControllerViewModel.setAbsoluteSpeed(deviceId  = it,speed=speed)
        }
        return speed;
    }

    var componentSize by remember { mutableIntStateOf(0) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(320.dp)
            .onGloballyPositioned { coordinates ->
                componentSize = coordinates.size.width
            }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    change.consume()
                    val center = Offset(componentSize / 2f, componentSize / 2f)
                    val dragOffset = change.position - center
                    var angle = Math.toDegrees(atan2(dragOffset.y.toDouble(), dragOffset.x.toDouble())).toFloat()
                    if (angle < 0) angle += 360f
                    val newSpeed = getSpeedFromAngle(angle)
                    if (newSpeed != currentSpeed) {
                        onSpeedChange(newSpeed)
                    }
                }
            }
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val center = Offset(componentSize / 2f, componentSize / 2f)
                    val tapOffset = offset - center
                    var angle = Math.toDegrees(atan2(tapOffset.y.toDouble(), tapOffset.x.toDouble())).toFloat()
                    if (angle < 0) angle += 360f
                    val newSpeed = getSpeedFromAngle(angle)
                    onSpeedChange(newSpeed)
                }
            }
    ) {
        // A. The Glowing Rings (Now Full Circles)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val radius = size.minDimension / 2 -50f // Slightly smaller to fit outside icon

            // Draw multiple rings for glow effect
            for (i in 0..5) {
                drawCircle(
                    color = GlowBlue.copy(alpha = 0.05f + (if (i < 2) 0.1f else 0f)),
                    radius = radius - (i * 10f),
                    style = Stroke(width = 3f)
                )
            }

            // Draw the Main Main outer Ring (Full 360)
            drawCircle(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        NeonCyan.copy(0.1f),
                        NeonCyan.copy(0.5f),
                        NeonCyan.copy(0.1f)
                    )
                ),
                radius = radius,
                style = Stroke(width = 6f)
            )
        }

        // B. The Numbers (1 to 6) - Still on the top half
        val numberRadius = 125.dp
        speedAngles.forEachIndexed { index, angle ->
            val number = index + 1
            val isSelected = number == currentSpeed
            val textColor = if (isSelected) Color.White else Color.White.copy(alpha = 0.5f)
            val fontSize = if (isSelected) 22.sp else 18.sp

            CircularElement(angle = angle, radius = numberRadius) {
                Text(
                    text = number.toString(),
                    color = textColor,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // C. Center Power Button
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(80.dp)
                .clickable {
                    deviceId?.let {
                        fanControllerViewModel.setPower(deviceId = it, isPower = true)
                    }
                }
        ) {
            Icon(
                painter = painterResource(R.drawable.power),
                contentDescription = "Power",
                tint = if (currentSpeed > 0) NeonCyan else Color.Gray,
                modifier = Modifier.size(48.dp)
            )
        }

        // D. The Drag Thumb (Simplified to just the glowing dot)
        val targetAngle = if (currentSpeed == 0) 180f else speedAngles.getOrElse(currentSpeed - 1) { 270f }
        val animatedAngle by animateFloatAsState(targetValue = targetAngle, label = "ThumbAnimation")

        if (currentSpeed > 0) {
            CircularElement(angle = animatedAngle, radius = 135.dp) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.White, CircleShape)
                        .border(4.dp, NeonCyan.copy(alpha = 0.5f), CircleShape)
                )
            }
        }

        // E. Static Energy Icon at Bottom Outside Circle
        // We place it at a slightly larger radius to be "outside"
        CircularElement(angle = 90f, radius = 160.dp) {
            Icon(
                painter = painterResource(R.drawable.energy),
                contentDescription = "Energy Status",
                tint = NeonCyan.copy(alpha = 0.8f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

// ... (Rest of your CircularElement, BottomControlRow, and GlassCard code remains exactly the same) ...

@Composable
fun CircularElement(angle: Float, radius: Dp, content: @Composable () -> Unit) {
    val rad = Math.toRadians(angle.toDouble())
    val offsetX = (radius.value * cos(rad)).dp
    val offsetY = (radius.value * sin(rad)).dp

    Box(
        modifier = Modifier.offset(x = offsetX, y = offsetY),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun BottomControlRow(fanControllerViewModel: FanControllerViewModel, deviceId: String?, timeHour: MutableState<Int>, isLight: MutableState<Boolean>, isSleep: MutableState<Boolean>, timerState:String, lightState: String, sleepState: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GlassCard(icon = R.drawable.timer, label = "Timer", state = timerState){
            deviceId?.let {
                timeHour.value = (timeHour.value+1)%9
                fanControllerViewModel.setTimer(deviceId = deviceId, time = timeHour.value)

            }
        }
        GlassCard(icon = R.drawable.light, label = "Light", state = lightState){
            deviceId?.let {id->
                isLight.value = !isLight.value
                fanControllerViewModel.setLight(deviceId = id,isLight = isLight.value )

            }
        }
        GlassCard(icon = R.drawable.sleep, label = "Sleep", state = sleepState){

            deviceId?.let {
                isSleep.value = !isSleep.value
                fanControllerViewModel.enableSleepMode(deviceId=it, isSleep = isSleep.value)
            }

        }
    }
}

@Composable
fun GlassCard(icon: Int, label: String, state: String, onClick:()-> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(85.dp)
            .height(130.dp)
            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .clickable {onClick() }
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = TextWhite,
            modifier = Modifier.size(28.dp)
        )
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(2.dp)
                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(1.dp))
        )
        Text(
            text = state,
            color = TextWhite,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

