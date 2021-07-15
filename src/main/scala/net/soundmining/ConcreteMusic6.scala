package net.soundmining

import net.soundmining.sound.{SoundPlay, SoundPlays}
import net.soundmining.synth.SuperColliderClient.loadDir
import net.soundmining.synth.{Instrument, SuperColliderClient}

object ConcreteMusic6 {

  implicit val client: SuperColliderClient = SuperColliderClient()
  val SYNTH_DIR = "/Users/danielstahl/Documents/Projects/soundmining-modular/src/main/sc/synths"
  val SOUND_DIR = "/Users/danielstahl/Documents/Music/sounds/Concrete Music 6_sounds/rendered_audio"

  val BIG_BOWL_2 = "big-bowl-2"
  val SMALL_BOWL_1 = "small-bowl-1"
  val MORTAR_RUMBLE_1 = "mortar-rumble-1"
  val MORTAR_HIT_1 = "mortar-hit-1"

  val soundPlays = SoundPlays(soundPlays = Map(
    BIG_BOWL_2 -> SoundPlay(s"$SOUND_DIR/Big Bowl 2.flac", 0.018, 3.222, spectrumFrequencies = Seq(469.726, 1156.29)),
    SMALL_BOWL_1 -> SoundPlay(s"$SOUND_DIR/Small Bowl 1.flac", 0.025, 1.454, spectrumFrequencies = Seq(1103.37, 2766.47)),
    MORTAR_RUMBLE_1 -> SoundPlay(s"$SOUND_DIR/Mortar Rumble 1.flac", 0.044, 1.309, spectrumFrequencies = Seq(), peakTimes = Seq(0.099, 0.228, 0.390, 0.503, 0.798, 0.864, 0.930, 1.030)),
    MORTAR_HIT_1 -> SoundPlay(s"$SOUND_DIR/Mortar Hit 1.flac", 0.051, 0.526)
  ))

  def init(): Unit = {
    println("Starting up SuperCollider client")
    client.start
    Instrument.setupNodes(client)
    client.send(loadDir(SYNTH_DIR))
    soundPlays.init
  }

  def development2(): Unit = {
    client.resetClock

    val absoluteTimes = Seq(0.228, 0.503, 0.864)
    val ratio = (absoluteTimes(1) - absoluteTimes.head) / (absoluteTimes(2) - absoluteTimes.head) // 0.43238993710691825
    val invertedRatio = 1 - ratio // 0.5676100628930818
    val longTime = invertedRatio * 6
    val shortTime = ratio * 6

    println(s"Longtime $longTime shortTime $shortTime")

    val times = Melody.absolute(0, Seq(
      longTime, longTime, longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime, shortTime * ratio,
      longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime * ratio,
      longTime, longTime
    ))


    val lowRate = 469.726 / 1156.29
    val lowLowRate = lowRate * lowRate

    val highRate = 1156.29 / 469.726
    val highHighRate = highRate * highRate
    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times.head, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times.head + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times.head + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(1), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.6)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(1) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 3.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.9).play(times(1) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 3.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.9).play(times(1) + 0.1, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(2), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(2) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(2) + 0.02, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(3), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(3) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(3) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(4), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(4) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(4) + 0.02, 0)


    val lowHighRate = 1103.37 / 2766.47
    val lowLowHighRate = lowHighRate * lowHighRate
    val highRate2 = 2766.47 / 1103.37


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(5), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(5) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(5) + 0.02, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(6), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.5).play(times(6) + 0.1, 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.5).play(times(6) + 0.1, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(7), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(7) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(7) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(8), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(8) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(8) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(9), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(9) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(9) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(10), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(10) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.9).play(times(10) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.9).play(times(10) + 0.1, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(11), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(11) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(11) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(12), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(12) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(12) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(13), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(13) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(13) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(14), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.5).play(times(14) + 0.1, 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.5).play(times(14) + 0.1, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(15), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(15) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(15) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(16), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(16) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.9).play(times(16) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.9).play(times(16) + 0.1, 0)
  }

  def development1(): Unit = {
    client.resetClock

    val absoluteTimes = Seq(0.228, 0.503, 0.864)
    val ratio = (absoluteTimes(1) - absoluteTimes.head) / (absoluteTimes(2) - absoluteTimes.head) // 0.43238993710691825
    val invertedRatio = 1 - ratio // 0.5676100628930818
    val longTime = invertedRatio * 8
    val shortTime = ratio * 8

    println(s"Longtime $longTime shortTime $shortTime")

    val times = Melody.absolute(0, Seq(
      longTime, longTime, longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime, shortTime * ratio,
      longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime * ratio,
      longTime, longTime
    ))

    val lowRate = 469.726 / 1156.29
    val lowLowRate = lowRate * lowRate

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times.head, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times.head + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times.head + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(1), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(1) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * lowRate)
      .pan(0.9).play(times(1) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.9).play(times(1) + 0.1, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(2), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(2) + 0.01, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(2) + 0.02, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(3), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(3) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(3) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(4), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(4) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(4) + 0.02, 0)


    val lowHighRate = 1103.37 / 2766.47
    val lowLowHighRate = lowHighRate * lowHighRate


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(5), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(5) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(5) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(6), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * lowHighRate)
      .pan(-0.5).play(times(6) + 0.1, 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.5).play(times(6) + 0.1, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(7), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(7) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(7) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(8), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(8) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(8) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(9), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(9) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(9) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(10), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(10) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * lowRate)
      .pan(0.9).play(times(10) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.9).play(times(10) + 0.1, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(11), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(11) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(11) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(12), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(12) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(12) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(13), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(13) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(13) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(14), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * lowHighRate)
      .pan(-0.5).play(times(14) + 0.1, 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.5).play(times(14) + 0.1, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(15), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(15) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(15) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(16), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(16) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * lowRate)
      .pan(0.9).play(times(16) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.9).play(times(16) + 0.1, 0)
  }

  def exposition(): Unit = {
    val absoluteTimes = Seq(0.228, 0.503, 0.864)
    val ratio = (absoluteTimes(1) - absoluteTimes.head) / (absoluteTimes(2) - absoluteTimes.head) // 0.43238993710691825
    val invertedRatio = 1 - ratio // 0.5676100628930818
    val longTime = invertedRatio * 10 // 5.6761006289308185
    val shortTime = ratio * 10 // 4.323899371069182

    val times = Melody.absolute(0, Seq(
      longTime, longTime, longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime, shortTime * ratio,
      longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime * ratio,
      longTime, longTime))

    println(s"Times $times")

    client.resetClock

    val lowRate = 469.726 / 1156.29
    val lowLowRate = lowRate * lowRate

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times.head, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times.head + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times.head + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(1), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 0.6)
      .pan(0).play(times(1) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowRate)
      .pan(0.9).play(times(1) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowLowRate)
      .pan(-0.9).play(times(1) + 0.1, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(2), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(2) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(2) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(3), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(3) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(3) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(4), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(4) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(4) + 0.02, 0)


    val lowHighRate = 1103.37 / 2766.47
    val lowLowHighRate = lowHighRate * lowHighRate

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(5), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(5) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(5) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(6), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 0.6)
      .pan(-0.5).play(times(6) + 0.1, 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.0)
      .sine(1103.37 * lowHighRate)
      .pan(0.5).play(times(6) + 0.1, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(7), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(7) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(7) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(8), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(8) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(8) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(9), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(9) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(9) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(10), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 0.6)
      .pan(0).play(times(10) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowRate)
      .pan(0.9).play(times(10) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowLowRate)
      .pan(-0.9).play(times(10) + 0.1, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(11), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(11) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(11) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(12), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(12) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(12) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(13), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(13) + 0.01, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(13) + 0.02, 0)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(14), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 0.6)
      .pan(-0.5).play(times(14) + 0.1, 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.0)
      .sine(1103.37 * lowHighRate)
      .pan(0.5).play(times(14) + 0.1, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(15), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(15) + 0.01, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(15) + 0.02, 0)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(16), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 0.6)
      .pan(0).play(times(16) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowRate)
      .pan(0.9).play(times(16) + 0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowLowRate)
      .pan(-0.9).play(times(16) + 0.1, 0)
  }

  def playMortal(attackTime: Double = 0.01, releaseTime: Double = 0.01): Unit = {
    client.resetClock

    val rate = 1
    val lowerRate = 469.726 / 1156.29

    soundPlays.mono(BIG_BOWL_2)
      .playMono(rate, 1.0)
      .pan(0).play(0, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(rate, 1.0)
      .pan(0).play(0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(rate, 1.0)
      .sine(469.726, attackTime = attackTime, releaseTime = releaseTime)
      .pan(-0.9).play(0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(rate, 1.0)
      .sine(469.726 * lowerRate * lowerRate, attackTime = attackTime, releaseTime = releaseTime)
      .pan(0.0).play(0.1, 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(rate, 1.0)
      .sine(1156.29, attackTime = attackTime, releaseTime = releaseTime)
      .pan(0.9).play(0.1, 0)
  }

  def playBowl(): Unit = {
    client.resetClock

    // 469.726 1156.29
    soundPlays.mono(BIG_BOWL_2)
      .playMono(1, 1.0)
      .pan(-0.2).play(0, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono( 469.726 / 1156.29, 1.0)
      .pan(0.5).play(2, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono( 1156.29 / 469.726, 1.0)
      .pan(0.2).play(4, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(1, 1.0)
      .pan(-0.2).play(6, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono( 1103.37 / 2766.47, 1.0)
      .pan(0.5).play(8, 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono( 2766.47 / 1103.37, 1.0)
      .pan(0.2).play(10, 0)
  }

  def playBigBowlMelody(speed: Double = 2, scale: Int = 0): Unit = {
    playBowlMelody(BIG_BOWL_2, 469.726, 1156.29, speed, scale)
  }

  def playSmallBowlMelody(speed: Double = 2, scale: Int = 0): Unit = {
    playBowlMelody(SMALL_BOWL_1, 1103.37, 2766.47, speed, scale)
  }

  def playBowlMelodyWithTime(scaleIndex: Int, melodyIndex: Int): Unit = {
    client.resetClock
    // Do the times with lowerRate or lowerLowerRate
    // ConcreteMusic6.soundPlays(ConcreteMusic6.MORTAL_RUMBLE_1).relativeTimes(Seq(0.228, 0.503, 0.798, 0.930), 0.16)
    List(1.15, 1.71875, 1.8437500000000004, 0.8249999999999993, 2.3687499999999995)

    val size = 5

    val lowerFreq = 469.726
    val higherFreq = 1156.29
    val lowerRate = lowerFreq / higherFreq
    val lowerLowerRate = lowerRate * lowerRate

    val lowerScale = Melody.absolute(lowerRate, Seq.fill(size + 1)((1 - lowerRate) / size))
    println(s"Lower scale $lowerScale rate $lowerRate")

    val lowerLowerScale = Melody.absolute(lowerLowerRate, Seq.fill(size + 1)((1 - lowerRate) / size))
    println(s"Lower scale $lowerLowerScale rate $lowerLowerRate")

    val higherRate = higherFreq / lowerFreq
    val higherScale = Melody.absolute(1, Seq.fill(size + 1)((higherRate - 1) / size))

    val higherHigherScale = Melody.absolute(higherRate, Seq.fill(size + 1)((higherRate - 1) / size))

    val scales = Seq(lowerLowerScale, lowerScale, higherScale, higherHigherScale)

    val scale = scales(scaleIndex)

    val sound = BIG_BOWL_2

    val plainMelody = Seq(0, 1, 3, 2, 4)
    val inversion = Seq(5, 4, 2, 3, 1)
    val retrograde = Seq(4, 2, 3, 1, 0)
    val retrogradeInversion = Seq(1, 3, 2, 4, 5)

    val melodies = Seq(plainMelody, inversion, retrograde, retrogradeInversion)
    val melody = melodies(melodyIndex)

    val relativeTimes = ConcreteMusic6.soundPlays(ConcreteMusic6.MORTAR_RUMBLE_1).relativeTimes(Seq(0.228, 0.503, 0.798, 0.930), lowerLowerRate * lowerRate)
    val times = Melody.absolute(0, relativeTimes)

    println(s"relative times $relativeTimes times $times")

    melody.zipWithIndex.foreach {
      case (note, pos) =>
        soundPlays.mono(sound)
          .playMono(scale(note), 1.0)
          .pan(0).play(times(pos), 0)
    }

  }

  def playBowlMelody(sound: String, lowerFreq: Double, higherFreq: Double, speed: Double = 2, scaleIndex: Int = 0): Unit = {
    client.resetClock

    val size = 5

    val lowerRate = lowerFreq / higherFreq

    val lowerScale = Melody.absolute(lowerRate, Seq.fill(size + 1)((1 - lowerRate) / size))
    println(s"Lower scale $lowerScale rate $lowerRate")

    val lowerLowerScale = Melody.absolute(lowerRate * lowerRate, Seq.fill(size + 1)((1 - lowerRate) / size))
    println(s"Lower scale $lowerLowerScale rate $lowerRate")

    val higherRate = higherFreq / lowerFreq
    val higherScale = Melody.absolute(1, Seq.fill(size + 1)((higherRate - 1) / size))

    val higherHigherScale = Melody.absolute(higherRate, Seq.fill(size + 1)((higherRate - 1) / size))

    val scales = Seq(lowerLowerScale, lowerScale, higherScale, higherHigherScale)

    val scale = scales(scaleIndex)

    val melody = Seq(0, 1, 3, 2, 4)
    val inversion = Seq(5, 4, 2, 3, 1)
    val retrograde = Seq(4, 2, 3, 1, 0)
    val retrogradeInversion = Seq(1, 3, 2, 4, 5)

    val fullMelody = melody ++ inversion ++ retrograde ++ retrogradeInversion

    fullMelody.zipWithIndex.foreach {
      case (note, pos) =>
        soundPlays.mono(sound)
          .playMono(scale(note), 1.0)
          .pan(0).play(pos * speed, 0)
    }


/*
    val higherRate = 1156.29 / 469.726

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowerRate, 1.0)
      .pan(0).play(0, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(higherRate, 1.0)
      .pan(-0.2).play(2, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(1, 1.0)
      .pan(0.2).play(4, 0)*/
  }

  def playPan(): Unit = {
    client.resetClock

    // 1540.05, 850.451

    var rate = 1
    soundPlays.mono("pan-sound")
      .playMono(rate, 1.0)
      .pan(0).play(0, 0)

    soundPlays.mono("pan-sound")
      .playMono(rate, 1.0)
      .sine(1540.05)
      .pan(-0.9).play(0, 0)

    soundPlays.mono("pan-sound")
      .playMono(rate, 1.0)
      .sine(850.451)
      .pan(0.9).play(0, 0)

    soundPlays.mono("pan-sound")
      .playMono(rate, 1.0)
      .pan(-0.5).play(5, 0)

    soundPlays.mono("pan-sound")
      .playMono(rate, 1.0)
      .sineFm(850.451, 1540.05, 300)
      .pan(0.5).play(5, 0)

    soundPlays.mono("pan-sound")
      .playMono(rate, 2.0)
      .ring((850.451 / 1540.05) * 850.451)
      .pan(-0.8).play(10, 0)

    soundPlays.mono("pan-sound")
      .playMono(rate, 1.0)
      .pan(0.8).play(10, 0)
  }

  def stop(): Unit = {
    println("Stopping SuperCollider client")
    client.stop
  }
}
