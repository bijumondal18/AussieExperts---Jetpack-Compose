package com.aussie.aussieexperts.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.filled.VideoCameraBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aussie.aussieexperts.core.base.BaseCircleAvatar
import com.aussie.aussieexperts.core.theme.Blue
import com.aussie.aussieexperts.core.theme.ErrorRed
import com.aussie.aussieexperts.core.theme.SuccessGreen

@Composable
fun CreatePostCard(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.8f), // darkest top
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.7f), // darkest top
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f), // darkest top
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), // darkest top
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.4f), // dark top
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), // dark top
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), // dark top
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), // light top
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.01f), // light bottom
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.001f)  // lightest bottom
                    )
                )
            )
            .statusBarsPadding()

    ) {
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Aussie Experts",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier.padding(16.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        Icons.Default.UploadFile,
                        contentDescription = "Upload File",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Upload Files",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                    )
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BaseCircleAvatar(imageUrl = "https://t4.ftcdn.net/jpg/04/31/64/75/360_F_431647519_usrbQ8Z983hTYe8zgA7t1XVc5fEtqcpa.jpg", size = 36.dp)
                Text(
                    text = "What's on your mind, Pankaj?",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

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