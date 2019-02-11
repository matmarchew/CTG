import 'package:flutter/material.dart';

class BoardSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Expanded(
        flex: 5,
        child: Row(children: <Widget>[
          Expanded(flex: 1, child: Container(color: Colors.lime))
        ]));
  }
}
