import 'package:client_app/views/main/betting_tile/BettingTileView.dart';
import 'package:flutter/material.dart';

class BettingTileSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Expanded(
        flex: 3,
        child: Row(children: <Widget>[
          BettingTileView(),
          BettingTileView(),
          BettingTileView(),
          BettingTileView(),
          BettingTileView()
        ]));
  }
}
