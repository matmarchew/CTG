import 'package:client_app/Client.dart';
import 'package:client_app/MyHomePage.dart';
import 'package:flutter/material.dart';

import 'package:client_app/state/Model.dart';

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MyHomePage(
          title: 'Camel: The Game',
          client: Client("192.168.99.100", 4444),
          model: Model()),
    );
  }
}
