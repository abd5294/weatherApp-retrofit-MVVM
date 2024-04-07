package com.abdur.weatherforecast.ui.weatherscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdur.weatherforecast.R

@Composable
fun WeatherScreen() {
    val vm: WeatherViewModel = hiltViewModel()
    val weather = vm.weatherState.value
    val normalFont = 28.sp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color
                            (255, 128, 0, 200), Color.White
                    )
                )
            ),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            TextField(
                value = vm.query.value,
                onValueChange = {
                    vm.onSearch(it)
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(237, 230, 211))
                    .align(Alignment.CenterHorizontally),
                placeholder = { Text(text = "Enter the location") },
                singleLine = true,
                leadingIcon = { Icons.Default.Search },
            )

            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))

            weather?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${weather.main.temp}°C",
                        style = TextStyle(
                            fontSize = 44.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.ExtraBold,
                            shadow = Shadow(Color.Cyan, Offset(2.0f, 2.0f), blurRadius = 4.0f)
                        )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.temp),
                        contentDescription = "temperature",
                        modifier = Modifier.size(44.dp)
                    )
                }

                val annotatedString = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = normalFont,
                        )
                    ) {
                        append("City: ")
                    }

                    withStyle(
                        SpanStyle(
                            fontSize = normalFont,
                            color = Color.Cyan,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append(weather.name)
                    }
                }
                Text(text = annotatedString)

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = normalFont
                        )
                    ) {
                        append("Feels like ")
                    }

                    withStyle(
                        SpanStyle(
                            fontSize = normalFont,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("${weather.main.feels_like}°C")
                    }
                })

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = normalFont
                            )
                        ) {
                            append("Humidity: ")
                        }
                        withStyle(
                            SpanStyle(
                                fontSize = normalFont,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("${weather.main.humidity}%")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Text(text = "Maximum Temp: ", fontSize = normalFont)
                    Text(
                        text = "${weather.main.temp_max}°C",
                        fontSize = normalFont,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Text(text = "Minimum Temp: ", fontSize = normalFont)
                    Text(
                        text = "${weather.main.temp_min}°C",
                        fontSize = normalFont,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Wind",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Text(text = "Speed: ", fontSize = normalFont)
                    Text(
                        text = "${weather.wind.speed}m/s",
                        fontSize = normalFont,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Text(text = "Degree: ", fontSize = normalFont)
                    Text(
                        text = "${weather.wind.deg}°",
                        fontSize = normalFont,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
