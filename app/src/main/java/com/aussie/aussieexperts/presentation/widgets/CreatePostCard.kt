package com.aussie.aussieexperts.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.VideoCameraBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aussie.aussieexperts.core.theme.Blue
import com.aussie.aussieexperts.core.theme.ErrorRed
import com.aussie.aussieexperts.core.theme.SuccessGreen

@Composable
fun CreatePostCard(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(color = MaterialTheme.colorScheme.background)

    ) {
        Column {
            Text(
                text = "What's on your mind, Pankaj?",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 12.dp, end = 12.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.CameraAlt,
                            contentDescription = "Camera",
                            tint = SuccessGreen,
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(18.dp)
                        )
                        Text(
                            text = "Photo/video",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(end = 6.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Icon(
                            Icons.Default.VideoCameraBack,
                            contentDescription = "Video",
                            tint = ErrorRed,
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(18.dp)
                        )
                        Text(
                            text = "Live video",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(end = 6.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Visibility,
                            contentDescription = "Eye",
                            tint = Blue,
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(18.dp)
                        )
                        Text(
                            text = "Check in",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(end = 6.dp)
                        )
                    }
                }
            }
        }
    }
}