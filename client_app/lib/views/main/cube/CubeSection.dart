import 'package:client_app/views/main/cube/CubeView.dart';
import 'package:flutter/material.dart';

class CubeSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Expanded(
        flex: 2,
        child: Row(children: <Widget>[
          CubeView(),
          CubeView(),
          CubeView(),
          CubeView(),
          CubeView()
        ]));
  }
}
