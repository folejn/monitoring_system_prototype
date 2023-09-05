raspivid -o - -t 0 -w 400 -h 200 -fps 12  | cvlc -vvv stream:///dev/stdin --sout '#rtp{sdp=rtsp://:8080/}' :demux=h264 --h264-fps=12
