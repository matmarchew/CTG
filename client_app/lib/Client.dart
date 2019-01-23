import 'dart:convert';
import 'dart:io';

import 'package:client_app/state/Model.dart';

class Client {
  Future<Socket> server;

  Client(String ip, int port) {
    server = Socket.connect(ip, port);
  }

  void sendMessage(Map<String, dynamic> json) {
    server.then((client) {
      client.writeln(jsonEncode(json));
    });
  }

  void updateModel(Model model) {
    server.then((client) {
      client.listen((data) {
        model.updateState(jsonDecode(String.fromCharCodes(data)));
      });
    });
  }
}