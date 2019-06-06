function grayscale(img, width, height) {

    for (var x = 0; x < height; x++) {
        for (var y = 0; y < width; y++) {

            var p = img.getRGB(y,x);

            var a = (p>>24)&0xff;
            var r = (p>>16)&0xff;
            var g = (p>>8)&0xff;
            var b = p&0xff;

            var avg = (r+g+b)/3;

            p = (a<<24) | (avg<<16) | (avg<<8) | avg;

            img.setRGB(y, x, p);
        }
    }
    return img;
}


function sepia(img, width, height) {
    for (var y = 0; y < width; y++) {
        for (var x = 0; x < height; x++) {
            var p = img.getRGB(y,x);

            var a = (p>>24)&0xff;
            var r = (p>>16)&0xff;
            var g = (p>>8)&0xff;
            var b = p&0xff;

            //calculate tr, tg, tb
            var tr = (0.393*r + 0.769*g + 0.189*b);
            var tg = (0.349*r + 0.686*g + 0.168*b);
            var tb = (0.272*r + 0.534*g + 0.131*b);

            //check condition
            if(tr > 255){
                r = 255;
            }else{
                r = tr;
            }

            if(tg > 255){
                g = 255;
            }else{
                g = tg;
            }

            if(tb > 255){
                b = 255;
            }else{
                b = tb;
            }

            //set new RGB value
            p = (a<<24) | (r<<16) | (g<<8) | b;

            img.setRGB(y, x, p);
        }
    }

    return img;
}
