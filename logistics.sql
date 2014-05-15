create database if not exists logistics;

use logistics;

create table if not exists Cells (
CellID int unsigned not null auto_increment,
X int unsigned not null,
Y int unsigned not null,
AccessibleFromUp boolean not null,
AccessibleFromRight boolean not null,
AccessibleFromDown boolean not null,
AccessibleFromLeft boolean not null,
SlopeInDegreesFromUp boolean not null,
SlopeInDegreesFromRight boolean not null,
SlopeInDegreesFromDown boolean not null,
SlopeInDegreesFromLeft boolean not null,
MaxWeight int unsigned not null,
MaxDiameter int unsigned not null,
primary key (CellID)
);

create table if not exists TransportAgents (
TransportAgentID int unsigned not null auto_increment,
MaxFramesMoveOneCellForward int unsigned not null,
MaxFramesMoveOneCellBackward int unsigned not null,
MaxFramesTurnClockwise90 int unsigned not null,
MaxFramesTurnClockwise180 int unsigned not null,
MaxFramesTurnCounterclockwise90 int unsigned not null,
MaxFramesTurnCounterclockwise180 int unsigned not null,
Weight int unsigned not null,
Length int unsigned not null,
Width int unsigned not null,
Height int unsigned not null,
MaxProductWeight int unsigned not null,
MaxProductLength int unsigned not null,
MaxProductWidth int unsigned not null,
MaxProductHeight int unsigned not null,
primary key (TransportAgentID)
);

create table if not exists Routes (
TransportAgentID int unsigned not null,
TimeFrame int unsigned not null,
CellID int unsigned not null,
primary key (TransportAgentID, TimeFrame),
foreign key (TransportAgentID) references TransportAgents(TransportAgentID) on delete cascade,
foreign key (CellID) references Cells(CellID) on delete cascade,
unique key (TimeFrame, CellID)
);
