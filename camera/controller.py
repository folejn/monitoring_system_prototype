from flask import Flask, jsonify

from driver import Driver

app = Flask(__name__)
driv = Driver()


@app.route('/up')
def up():
    driv.up()
    return jsonify({'message': 'success'})

@app.route('/down')
def down():
    driv.down()
    return jsonify({"message": "success"})

@app.route('/right')
def right():
    driv.right()
    return jsonify({"message": "success"})

@app.route('/left')
def left():
    driv.left()
    return jsonify({"message": "success"})

@app.route('/shutdown')
def shutdown():
    driv.destruct()
    return jsonify({"message": "success"})

if __name__=='__main__':
    app.run(host='0.0.0.0', port=80)