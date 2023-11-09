package pl.zagzy.daznstreamer.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.zagzy.daznstreamer.domain.model.Event

@Composable
fun EventRow(event: Event) {

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(4.dp)
        ) {

            Text(event.title)

        }
    }


}
