import 'package:client_app/views/main/betting_card/BettingCardView.dart';
import 'package:flutter/material.dart';

class BettingCardSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Expanded(
        flex: 4,
        child: Row(children: <Widget>[BettingCardView(), BettingCardView()]));
  }
}
