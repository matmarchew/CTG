import 'package:flutter/material.dart';

class BettingCardView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    double height = MediaQuery.of(context).size.height;
    return Expanded(
        flex: 1,
        child: Container(
            child: Center(
                child: Container(
                    constraints: BoxConstraints.tight(Size.square(height / 5)),
                    decoration: ShapeDecoration(
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(20)),
                        color: Colors.grey)))));
  }
}
