import 'package:client_app/Client.dart';
import 'package:client_app/views/MyHomePage.dart';
import 'package:flutter/material.dart';
import 'package:client_app/state/Model.dart';

class MyApp extends StatelessWidget {
  static String login = "test";
  static Client client = Client("192.168.99.100", 4444);
  static Model model = Model();

  @override
  Widget build(BuildContext context) {
    client.sendMessage({"LOGIN": login});
    client.updateModel(model);
    return MaterialApp(
        home: MyHomePage(
      title: 'Camel: The Game',
      client: client,
      model: model,
    ));
  }
}
