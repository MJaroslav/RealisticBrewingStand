# RealisticBrewingStand

Little logical fix (in render) of the brewing stand.

[![Curseforge](https://cf.way2muchnoise.eu/272921.svg)](https://www.curseforge.com/minecraft/mc-mods/realistic-brewing-stand/ "Curseforge")
[![Curseforge versions](https://cf.way2muchnoise.eu/versions/272921.svg)](https://www.curseforge.com/minecraft/mc-mods/realistic-brewing-stand/files/all "Curseforge all files")
[<img src='https://raw.githubusercontent.com/modrinth/art/main/Branding/Wordmark/wordmark-light.svg' height='20px'></img>](https://modrinth.com/mod/realistic-brewing-stand/ "Modrinth")
[![GitHub issues](https://img.shields.io/github/issues/mjaroslav/realisticbrewingstand)](https://github.com/mjaroslav/realisticbrewingstand/issues "GitHub issues")
[![GitHub forks](https://img.shields.io/github/forks/mjaroslav/realisticbrewingstand)](https://github.com/mjaroslav/realisticbrewingstand/network "GitHub forks")
[![GitHub stars](https://img.shields.io/github/stars/mjaroslav/realisticbrewingstand)](https://github.com/mjaroslav/realisticbrewingstand/stargazers "GitHub stars")
[![GitHub license](https://img.shields.io/github/license/mjaroslav/realisticbrewingstand)](https://github.com/MJaroslav/realisticbrewingstand/blob/master/LICENSE "Open license")
[![JitPack](https://jitpack.io/v/MJaroslav/realisticbrewingstand.svg)](https://jitpack.io/#MJaroslav/realisticbrewingstand "JitPack")

## About repository structure

This repository uses a branch per MC version structure with `GAMEVERSION` name, for example `1.7.10`. If I port this to
Forge in Fabric supported MC versions than I will add a `-forge` suffix to branch name.

`master` branch uses for contain README.md, LICENSE and other shared files. I will sync it with other branches. **Search
CHANGELOG.md (if it exists) there for _released_ versions.**

As main GitHub branch uses the newest game version from presented (fabric more prefer than forge). Not `master` just
for commits statistic counting on profile page :)

**Current README file for 1.7.10 Forge version.**

## FAQ

Q: Why not client-side?  
A: Vanilla Brewing Stand don't sync their inventory. I made hook to fix this.

Q: Can you port this to MC 1.X.X?  
A: I port this to "LTS" versions (1.7.10, 1.12.2, 1.16.5) and last release if I can do this on my bad PC. You can fork
and port it by yourself (see [LICENSE](https://github.com/MJaroslav/RealisticBrewingStand/blob/master/LICENSE)).

Q: I know how to do it fully client side. Where I can tell you about it?
A: Just make pull request or issue with explanation.

Q: X mod have I custom brewing stand or something. Can you add support for this too?  
A: I can try or do it by yourself in fork or pull request. But this block can be relatable to this mod name. Why I
should do support for "showing speedometer item on car panel" if mod called "RealisticBrewingStand".

## Usage

### Dependencies

This mod required those next mods:

- MixinBooterLegacy

### Building

Just clone repository, checkout to this branch and run `./gradlew build`. If you get any MC source problem than run
`./gradlew setupDecompWorkspace`.

## Post Scriptum

Feel free to correct typos and errors in the text or code :)
